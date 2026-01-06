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

    public void AddBooking(Booking booking) {
        AllBookings.add(booking);
        System.out.println("Booking added to system");
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
            if (booking.getRoom().getOwnerID().equalsIgnoreCase(student.getUserID())) {
                StudentBookings.add(booking);
            }
        }
        return StudentBookings;
    }

}