import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    // additional attributes specific to Student
    private List<Booking> bookings;
    private SearchCriteria searchCriteria;

    // constructor
    public Student(String username, String password, String userType, String contactNumber) {
        super(username, password, userType, contactNumber);
        this.bookings = new ArrayList<>();
        // default values for search criteria
        this.searchCriteria = new SearchCriteria(null, null, null, null);
    }

    // Student specific methods
    // make a booking
    public void MakeBooking(Room room){
        Booking newBooking = new Booking(this, room);
        bookings.add(newBooking);
        BookingManager.GetInstance().AddBooking(newBooking);
        System.out.println("Booking " + newBooking.getBookingID() + " created for Room " + room.getRoomID());
    }

    // set search criteria for room search
    public void SetSearchCriteria(Double MinRent, Double MaxRent,Boolean furnished, Boolean available){
        this.searchCriteria = new SearchCriteria(MinRent, MaxRent, furnished, available);
        System.out.println("Search criteria updated for Student " + this.getUserID());
    }


    // extra getters for Student
    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public List<Booking> StudentBookings(){
        return this.bookings;
    }
}
