
public class Booking {
    // attributes 
    private String bookingID;
    private String StudentID;
    public Room room;
    private Status BookingStatus;

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

    // confirm booking status
    public Status ConfirmBookingStatus(Booking booking){
        return this.BookingStatus = Status.CONFIRMED;
    }

    // reject booking status 
    public Status RejectBookingStatus(Booking booking){
        return this.BookingStatus = Status.REJECTED;
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
