
public class Booking {
    // attributes 
    private String bookingID;
    private String StudentID;
    public Room room;
    Status BookingStatus;

    // constructor
    public Booking(Student student, Room room) {
        this.bookingID = CreateBookingID();
        this.StudentID = student.getUserID();
        this.room = room;
        this.BookingStatus = Status.PENDING;
    }

    // generate bookingID
    public static String CreateBookingID(){
        return "B-" + java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    // getters
    public String getBookingID() {
        return bookingID;
    }
    public String getUserID() {
        return StudentID;
    }
    public Room getRoom() {
        return room;
    }
    public Status getBookingStatus() {
        return BookingStatus;
    }

}
enum Status {
    PENDING,
    CONFIRMED,
    REJECTED
}