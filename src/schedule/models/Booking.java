
package schedule.models;

import java.sql.Timestamp;

/**
 *
 * @author Andris Jansons
 */

public class Booking implements Comparable {
    private int ID;
    private String title;
    private String description;
    private Timestamp start;
    private Timestamp finish;
    private int spaces;
    private Integer instructorID;
    private String location;
    private boolean available;
    private boolean cancelled;
    private TYPE type;
    
    @Override
    public int compareTo(Object o) {
         long com = this.getStart().getTime() - ((Booking) o).getStart().getTime();
         if(com>0){
             return 1;
         } else if(com < 0){
             return -1;
         } else {
             return 0;
         }
    }
    
    public Booking(){}
    
    public Booking(int ID, String title, String description, long startTimeInMillis, long finishTimeInMillis, int spaces, int instructorID, String location, boolean available, boolean cancelled, TYPE type){
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.start = new Timestamp(startTimeInMillis);
        this.finish = new Timestamp(finishTimeInMillis);
        this.spaces = spaces;
        this.instructorID = instructorID;
        this.location = location;
        this.available = available;
        this.cancelled = cancelled;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }
    
    public void setStart(long start){
        this.start = new Timestamp(start);
    }

    public Timestamp getFinish() {
        return finish;
    }

    public void setFinish(Timestamp finish) {
        this.finish = finish;
    }
    
    public void setFinish(long finish){
        this.finish = new Timestamp(finish);
    }

    public int getSpaces() {
        return spaces;
    }

    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    public Integer getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(Integer instructorID) {
        this.instructorID = instructorID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
    
    
}
