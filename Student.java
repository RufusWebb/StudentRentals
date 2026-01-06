import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    // additional attributes specific to Student
    private List<Booking> bookings;
    //private SearchStrategy searchStrategy;

    // constructor
    public Student(String username, String password, String userType, String contactNumber) {
        super(username, password, userType, contactNumber);
        this.bookings = new ArrayList<>();
        //this.searchStrategy = new DefaultSearchStrategy();
    }

    // Student specific methods
    public void MakeBooking(Room room){
        Booking newBooking = new Booking(this, room);
        bookings.add(newBooking);
        BookingManager.GetInstance().AddBooking(newBooking);
        System.out.println("Booking " + newBooking.getBookingID() + " created for Room " + room.getRoomID());
    }

    // getters 
    
}
