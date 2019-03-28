package schedule.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import schedule.models.*;

/**
 *
 * @author Andris Jansons
 */
public class InstructorRepo implements Repo {

    private ArrayList<Instructor> instructors;

    @Override
    public ArrayList read(Connection conn) {
        //Return all entries by not providing filters
        return read(conn, null, null, null);
    }

    public ArrayList read(Connection conn, Integer id, String firstName, String lastName) {

        System.out.println("Reding from the database");
        ArrayList list = new ArrayList();
        try {
            
            Statement st = conn.createStatement();
            String sql;
            
            //Filter using id, first name and last name. If no filters provided,
            //get all results instead
            if (id != null) {
                sql = "SELECT * FROM INSTRUCTORS WHERE ID=" + id;
            } else if (firstName != null && lastName != null) {
                sql = "SELECT * FROM INSTRUCTORS WHERE FIRSTNAME='" 
                        + firstName + "' AND LASTNAME='" + lastName + "'";
            } else if (firstName != null) {
                sql = "SELECT * FROM INSTRUCTORS WHERE FIRSTNAME='" + firstName + "'";
            } else if (lastName != null) {
                sql = "SELECT * FROM INSTRUCTORS WHERE LASTNAME='" + lastName + "'";
            } else {
                sql = "SELECT * FROM INSTRUCTORS";
            }

            ResultSet rs;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setID(rs.getInt("ID"));
                instructor.setFirstName(rs.getString("FIRSTNAME"));
                instructor.setLastName(rs.getString("LASTNAME"));

                list.add(instructor);
                System.out.println("Loaded instructor with ID: " + instructor.getID() + " and name: " + instructor.getFirstName() + instructor.getLastName());
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException was thrown while reading");
        }

        instructors = list;
        return instructors;
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
            Instructor instructor = (Instructor) list.get(i);
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO INSTRUCTORS VALUES ("
                        + "?, ?, ?)");

                ps.setInt(1, instructor.getID());
                ps.setString(2, instructor.getFirstName());
                ps.setString(3, instructor.getLastName());

                ps.executeUpdate();

                ps.close();
            } catch (SQLException ex) {
                System.out.println("SQLException was thrown while inserting bookings");
            }
        }
    }

}
