package schedule.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import schedule.models.*;

/**
 *
 * @author Andris Jansons
 */
public class BookingRepo implements Repo {

    private ArrayList<Booking> bookings;

    @Override
    public ArrayList read(Connection conn) {
        //Return all entries by not providing filters
        return read(conn, null, null, null, null);
    }

    public ArrayList read(Connection conn, Integer type, Timestamp startTime, Timestamp endTime, Integer instructorID) {
        System.out.println("Reading from the database");
        ArrayList list = new ArrayList();
        try {
            PreparedStatement st = null;

            String sql = "SELECT * FROM BOOKINGS";

            if (startTime != null && endTime != null) {
                sql = "SELECT * FROM BOOKINGS WHERE START>? AND FINISH<?";
                if (instructorID != null) {
                    sql += " AND INSTRUCTORID=" + instructorID;
                }
                if (type != null) {
                    sql += " AND TYPE=" + type;
                }
                st = conn.prepareStatement(sql);
                st.setTimestamp(1, startTime);
                st.setTimestamp(2, endTime);
            } else if (instructorID != null) {
                sql = "SELECT * FROM BOOKINGS WHERE INSTRUCTORID=" + instructorID;
                st = conn.prepareStatement(sql);
            } else if (type != null) {
                sql = "SELECT * FROM BOOKINGS WHERE TYPE=" + type;
                st = conn.prepareStatement(sql);
            }

            ResultSet rs;
            rs = st.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setAvailable(rs.getBoolean("AVAILABLE"));
                booking.setCancelled(rs.getBoolean("CANCELLED"));
                booking.setDescription(rs.getString("DESCRIPTION"));
                booking.setFinish(rs.getTimestamp("FINISH"));
                booking.setID(rs.getInt("ID"));
                booking.setInstructorID(rs.getInt("INSTRUCTORID"));
                booking.setLocation(rs.getString("LOCATION"));
                booking.setSpaces(rs.getInt("SPACES"));
                booking.setStart(rs.getTimestamp("START"));
                booking.setTitle(rs.getString("TITLE"));
                booking.setType(TYPE.fromInt(rs.getInt("TYPE")));

                list.add(booking);
                System.out.println("Loaded booking with ID: " + booking.getID() + " and title: " + booking.getTitle());
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException was thrown while reading");
        }
        System.out.println("Read " + list.size() + " entries");
        bookings = list;
        return bookings;
    }

    @Override
    public void write(Connection conn, ArrayList list) {
        System.out.println("Writing to database...");
        System.out.println("Record size: " + list.size());
        Statement st;

        try {
            st = conn.createStatement();
            String sql = "DELETE FROM BOOKINGS";
            st.executeUpdate(sql);

            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException was thrown while writing");
        }

        for (int i = 0; i < list.size(); i++) {
            Booking booking = (Booking) list.get(i);
            try {
                PreparedStatement ps2 = conn.prepareStatement(
                        "INSERT INTO BOOKINGS VALUES ("
                        + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps2.setInt(1, booking.getID());
                ps2.setString(2, booking.getTitle());
                ps2.setString(3, booking.getDescription());
                ps2.setTimestamp(4, booking.getStart());
                ps2.setTimestamp(5, booking.getFinish());
                ps2.setInt(6, booking.getSpaces());
                ps2.setInt(7, booking.getInstructorID());
                ps2.setString(8, booking.getLocation());
                ps2.setBoolean(9, booking.isAvailable());
                ps2.setBoolean(10, booking.isCancelled());
                ps2.setInt(11, booking.getType().getValue());

                ps2.executeUpdate();

                ps2.close();
            } catch (SQLException ex) {
                System.out.println("SQLException was thrown while inserting bookings");
            }
        }
    }

}
