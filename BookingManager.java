import java.util.ArrayList;
import java.util.List;

public class BookingManager{

    private static BookingManager instance = null;
    private List<Booking> AllBookings;

    private BookingManager() {
        this.AllBookings = new ArrayList<>();
    }

    public static BookingManager GetInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    // add booking to system
    public void AddBooking(Booking booking) {
        AllBookings.add(booking);
        System.out.println("Booking added to system");
    }

    // remove booking from system
    public void RemoveBooking(Booking booking) {
        AllBookings.remove(booking);
        System.out.println("Booking removed from system");
    }

    // update booking in system
    public void UpdateBooking(Booking booking) {
        // replace existing booking with updated booking using index to avoid concurrent modification
        for (Booking existingBooking : AllBookings) {
            if (existingBooking.getBookingID().equalsIgnoreCase(booking.getBookingID())) {
                AllBookings.set(AllBookings.indexOf(existingBooking), booking);
                System.out.println("Booking " + booking.getBookingID() + " updated in system");
                return;
            }
        }
        // if not found, add it
        AllBookings.add(booking);
        System.out.println("Booking " + booking.getBookingID() + " added to system");
    }

    // getting all bookings for a specific homeowner
    public List<Booking> GetBookingsForHomeowner(Homeowner homeowner) {
        List<Booking> HomeownerBookings = new ArrayList<Booking>();
        for (Booking booking : AllBookings){
            if (booking.getRoom().getOwnerID().equalsIgnoreCase(homeowner.getUserID())) {
                HomeownerBookings.add(booking);
            }
        }
        return HomeownerBookings;
    }

    // getting all bookings for a specific student
    public List<Booking> GetBookingsForStudent(Student student) {
        List<Booking> StudentBookings = new ArrayList<Booking>();
        for (Booking booking : AllBookings){
            if (booking.getUserID().equalsIgnoreCase(student.getUserID())) {
                StudentBookings.add(booking);
            }
        }
        return StudentBookings;
    }

    // get all bookings
    public List<Booking> GetAllBookings() {
        return new ArrayList<>(AllBookings);
    }

    // get booking by ID
    public Booking GetBookingByID(String bookingID) {
        for (Booking booking : AllBookings){
            if (booking.getBookingID().equalsIgnoreCase(bookingID)){
                return booking;
            }
        }
        return null;
    }
}