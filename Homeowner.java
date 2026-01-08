import java.util.ArrayList;
import java.util.List;

public class Homeowner extends User{

    // additional attributes specific to Homeowner
    private int PropertyCount;
    private List<Room> RoomListings;

    // constructor
    public Homeowner(String username, String password, String userType, String contactNumber) {
        super(username, password, userType, contactNumber);
        this.PropertyCount = 0;
        this.RoomListings = new ArrayList<>();
    }

    // Homeowner specific methods

    // creating a new room method
    public Room CreateRoomListing(String location, double rent, boolean isAvailable, boolean isFurnished, String description){
        //create new room object
        Room NewRoom = new Room(this.getUserID(), location, rent, isAvailable, isFurnished, description);

        // add to listings
        RoomListings.add(NewRoom);
        // add to system-wide room manager
        RoomManager.GetInstance().AddRoom(NewRoom);
        System.out.println("Room " + NewRoom.getRoomID() + "created and added to listings");
        // increment propertyCount
        PropertyCount++;
        // return room
        return NewRoom;
    }

    // remove specific room method
    public boolean RemoveRoom(String RoomID){
        for (int i = 0; i < RoomListings.size(); i++){
            if (RoomListings.get(i).getRoomID().equals(RoomID)){
                Room removed = RoomListings.remove(i);
                RoomManager.GetInstance().RemoveRoom(removed);
                PropertyCount--;
                System.out.println("Room " + removed.getRoomID() + "was removed from listings");
                return true;
            }
        }
        System.out.println("Error: Room " + RoomID + "not found");
        return false;
    }

    // update specific room details
    public boolean UpdateRoomDetails(String RoomID, String location, Double rent, Boolean isAvailable, Boolean isFurnished, String description){
        for (Room room : RoomListings){
            if (room.getRoomID().equals(RoomID)){
                // update details
                if (location != null) room.setLocation(location);
                if (rent != null) room.setRent(rent);
                if (isAvailable != null) room.setAvailability(isAvailable);
                if (isFurnished != null) room.setFurnishing(isFurnished);
                if (description != null) room.setDescription(description);
                //announce update
                System.out.println("Room " + RoomID + " details updated");
                return true;
            }
        }
        System.out.println("Error: Room " + RoomID + " not found");
        return false;
    }

    // booking management methods
    // confirm booking
    public boolean ConfirmBooking(Booking booking){
        if (this.getUserID().equalsIgnoreCase(booking.room.getOwnerID())){
            // change booking status to confirmed
            booking.ConfirmBookingStatus(booking);   
            booking.room.setAvailability(false);
            // update booking in booking manager
            BookingManager.GetInstance().UpdateBooking(booking);
            return true;
        }
        else
            return false;
    }

    // reject booking
    public boolean RejectBooking(Booking booking){
        if (this.getUserID().equalsIgnoreCase(booking.room.getOwnerID())){
            // change booking status to rejected
            booking.RejectBookingStatus(booking);       
            // update booking in booking manager
            BookingManager.GetInstance().UpdateBooking(booking);
            return true;
        }
        else
            return false;
    }

    // view pending bookings
    public void DisplayAllBookings(){
        System.out.println("Bookings for homeowner: " + this.getUserID());
        List<Booking> myBookings = BookingManager.GetInstance().GetBookingsForHomeowner(this);
        // print list of all bookings for specific homeowner
        for (Booking booking : myBookings) {
            System.out.println("Booking ID: " + booking.getBookingID() +
                            ", Room ID: " + booking.getRoom().getRoomID() +
                            ", Student ID: " + booking.getUserID() +
                            ", Status: " + booking.getBookingStatus());
        }
    }

    //display all pending bookings
    public void DisplayAllPendingBookings(){
        System.out.println("Pending Bookings for homeowner: " + this.getUserID());
        List<Booking> myBookings = BookingManager.GetInstance().GetBookingsForHomeowner(this);
        // print list of all bookings for specific homeowner
        for (Booking booking : myBookings) {
            if (booking.getBookingStatus() == Status.PENDING){
                System.out.println("Booking ID: " + booking.getBookingID() +
                                ", Room ID: " + booking.getRoom().getRoomID() +
                                ", Student ID: " + booking.getUserID() +
                                ", Status: " + booking.getBookingStatus());
            }
        }
    }
    
    // show all rooms listed by homeowner
    public void DisplayAllRooms() {
        if (RoomListings.isEmpty()) {
            System.out.println("No rooms listed.");
            return;
        }
        
        System.out.println("Listed Rooms for " + getUsername() +":");
        for (Room room : RoomListings) {
            System.out.println("Room ID: " + room.getRoomID());
            System.out.println("Location: " + room.getLocation());
            System.out.println("Rent: £" + room.getRent());
            System.out.println("Available: " + (room.isAvailable() ? "Yes" : "No"));
            System.out.println("Furnished: " + (room.isFurnished() ? "Yes" : "No"));
            System.out.println("Description: " + room.getDescription());
            System.out.println("---");
        }
    }

    // getters and setters
    public int getPropertyCount(){
        return PropertyCount;
    }

    public List<Room> GetListedRooms(){
        return new ArrayList<>(RoomListings);
    }


}
