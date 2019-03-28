package schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import schedule.models.Booking;
import schedule.models.Instructor;
import schedule.models.TYPE;
import schedule.repo.BookingRepo;
import schedule.repo.InstructorRepo;

/**
 *
 * @author Andris Jansons
 */
public class ScheduleController {

    //our only instance of ScheduleController
    private static ScheduleController instance;

    //Database connection information
    Connection conn;
    String connectionURL = "jdbc:derby://localhost:1527/ScheduleDB";
    String uName = "andris";
    String uPass = "andris";

    //make the constructor private so that this class cannot be instantiated
    private ScheduleController() {
        try {
            conn = DriverManager.getConnection(connectionURL, uName, uPass);
            if (conn != null) {
                System.out.println("Connected to database");
            } else {
                System.out.println("Failed to connect to the database");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new ScheduleSelectUI().start();
    }

    //Get the only object available or make the object if it doesn't exist
    public static ScheduleController getInstance() {
        if (instance == null) {
            instance = new ScheduleController();
        }
        return instance;
    }

    public void openPersonSchedule(String idString, String firstName, String lastName, PERIOD period) {
        //Convert parameters to make them valid for a database query
        firstName = convertToNullIfEmpty(firstName);
        lastName = convertToNullIfEmpty(lastName);
        Integer id = convertToPositiveInt(idString.trim());
        
        Calendar cal = Calendar.getInstance();
        Timestamp startTime = new Timestamp(cal.getTimeInMillis());
        //Add appropriate interval between start and end time
        cal = addInterval(cal, period);
        Timestamp endTime = new Timestamp(cal.getTimeInMillis());
        
        InstructorRepo instructorRepo = new InstructorRepo();
        BookingRepo bookingRepo = new BookingRepo();
        
        ArrayList<Booking> bookings = new ArrayList<>();
        //Get all matching instructors
        ArrayList<Instructor> instructors = instructorRepo.read(conn, id, firstName, lastName);
        
        if(instructors.isEmpty()){
            showMessageDialog(null, "No instructors found");
            return;
        }
        
        instructors.forEach((instructor) -> {
            bookings.addAll(bookingRepo.read(conn, null, startTime, endTime, instructor.getID()));
        });
        
        new ScheduleUI().start(bookings);
        
    }

    public void openPeriodSchedule() {

    }

    public void openUsageSchedule() {

    }
    
    /**
     * Increases @param cal instance by the specified amount in @param period 
     * @param cal Calendar instance
     * @param period PERIOD of either week, month or year
     * @return updated calendar instance
     */
    private Calendar addInterval(Calendar cal, PERIOD period){
        if(null != period)
            switch (period) {
            case WEEK:
                cal.add(Calendar.WEEK_OF_YEAR, 1);
                break;
            case MONTH:
                cal.add(Calendar.MONTH, 1);
                break;
            case YEAR:
                cal.add(Calendar.YEAR, 1);
                break;
            default:
                break;
        }
        return cal;
    }

    /**
     * If passed string is null, only contains whitespace or is empty
     * return null otherwise return the string with removed whitespace
     * at the edges
     * @param text string to convert
     * @return null or string
     */
    private String convertToNullIfEmpty(String text) {
        if (text != null) {
            text = text.trim();
            if ("".equals(text)) {
                //If firstName is empty, set it to null
                text = null;
            }
        }
        return text;
    }

    /**
     * Converts string to a positive integer or returns null
     *
     * @param id string to convert
     * @return positive integer or null
     */
    private Integer convertToPositiveInt(String id) {
        if (!isNumeric(id)) {
            return null;
        }
        int result = Integer.parseInt(id);
        if (result < 0) {
            return null;
        }
        return result;
    }

    /**
     * Checks if a string is numeric
     *
     * @param str string to check
     * @return true if numeric
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void addDummyDataToDB() {
        System.out.println("Started");
        ArrayList<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Booking booking = new Booking();
            booking.setAvailable(true);
            booking.setCancelled(false);
            booking.setDescription("Description");
            booking.setTitle("Title");

            Calendar time = Calendar.getInstance();
            booking.setStart(time.getTimeInMillis());
            time.add(Calendar.HOUR, 2);
            booking.setFinish(time.getTimeInMillis());

            booking.setID(i);
            booking.setInstructorID(0);
            booking.setLocation("ECG-24");
            booking.setSpaces(i);
            booking.setType(TYPE.CLASS);

            bookings.add(booking);
        }
        if (conn != null) {
            BookingRepo repo = new BookingRepo();
            repo.write(conn, bookings);
        }
    }
}
