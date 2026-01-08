import java.util.List;

public class Admin extends User{
    private String AdminID;

    public Admin(String username, String password, String userType, String contactNumber) {
        super(username, password, userType, contactNumber);
        this.AdminID = CreateUserID(userType);
    }

    // view all users in the system
    public void ViewAllUsers() {
        List<User> allUsers = UserManager.GetInstance().GetAllUsers();
        System.out.println("\n|| All Users in System ||");
        System.out.println("Total Users: " + allUsers.size());
        for (User user : allUsers) {
            System.out.println("User ID: " + user.getUserID() +
                            ", Username: " + user.getUsername() +
                            ", Type: " + user.getUserType() +
                            ", Contact: " + user.getContactNumber());
        }
    }

    // view all rooms in the system
    public void ViewAllRooms() {
        List<Room> allRooms = RoomManager.GetInstance().GetAllRooms();
        System.out.println("\n|| All Rooms in System ||");
        System.out.println("Total Rooms: " + allRooms.size());
        for (Room room : allRooms) {
            System.out.println("Room ID: " + room.getRoomID() +
                            ", Location: " + room.getLocation() +
                            ", Rent: " + room.getRent() +
                            ", Available: " + room.isAvailable() +
                            ", Furnished: " + room.isFurnished() +
                            ", Owner ID: " + room.getOwnerID());
        }
    }
    // delete a user by userID
    public void DeleteUser(String username){
        UserManager userManager = UserManager.GetInstance();
        User userToDelete = userManager.FindByUsername(username);
        if (userToDelete != null) {
            userManager.RemoveUser(userToDelete.getUserID());
            System.out.println("User " + username + " deleted successfully.");
        } else {
            System.out.println("User " + username + " not found.");
        }
    }
    // view all bookings in the system
    public void ViewAllBookings() {
        List<Booking> allBookings = BookingManager.GetInstance().GetAllBookings();
        System.out.println("\n|| All Bookings in System ||");
        System.out.println("Total Bookings: " + allBookings.size());
        for (Booking booking : allBookings) {
            System.out.println("Booking ID: " + booking.getBookingID() +
                            ", Room ID: " + booking.getRoom().getRoomID() +
                            ", Student ID: " + booking.getUserID());
        }
    }
    // generate system report
    public void GenerateSystemReport() {
        UserManager userManager = UserManager.GetInstance();
        RoomManager roomManager = RoomManager.GetInstance();
        BookingManager bookingManager = BookingManager.GetInstance();

        int totalUsers = userManager.GetAllUsers().size();
        int totalStudents = userManager.GetAllStudents().size();
        int totalHomeowners = userManager.GetAllHomeowners().size();
        int totalAdmins = userManager.GetAllAdmins().size();
        int totalRooms = roomManager.GetAllRooms().size();
        int totalBookings = bookingManager.GetAllBookings().size();

        System.out.println("\n|| System Report ||");
        System.out.println("Total Users: " + totalUsers);
        System.out.println(" - Students: " + totalStudents);
        System.out.println(" - Homeowners: " + totalHomeowners);
        System.out.println(" - Admins: " + totalAdmins);
        System.out.println("Total Rooms Listed: " + totalRooms);
        System.out.println("Total Bookings Made: " + totalBookings);
    }
    
    // getters
    public String getAdminID() {
        return AdminID;
    }
}
