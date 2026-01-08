
public class Test {
    
    public static void InitialiseTestData() {
        //get singleton instances of managers
        UserManager userManager = UserManager.GetInstance();
        RoomManager roomManager = RoomManager.GetInstance();
        BookingManager bookingManager = BookingManager.GetInstance();

        // Create test students using StudentFactory
        Student student1 = new StudentFactory().CreateUser("john_student", "pass123", "078901234567");
        Student student2 = new StudentFactory().CreateUser("emma_student", "pass456", "078901234568");
        Student student3 = new StudentFactory().CreateUser("mike_student", "pass789", "078901234569");
        Student student4 = new StudentFactory().CreateUser("lisa_student", "pass321", "078901234570");
        Student student5 = new StudentFactory().CreateUser("tom_student", "pass654", "078901234571");
        
        userManager.AddUser(student1);
        userManager.AddUser(student2);
        userManager.AddUser(student3);
        userManager.AddUser(student4);
        userManager.AddUser(student5);
        // Create test homeowners using HomeownerFactory
        Homeowner homeowner1 = new HomeownerFactory().CreateUser("sarah_owner", "owner123", "023454333423");
        Homeowner homeowner2 = new HomeownerFactory().CreateUser("david_owner", "owner456", "089892356355");
        Homeowner homeowner3 = new HomeownerFactory().CreateUser("anna_owner", "owner789", "098956748384");
        
        userManager.AddUser(homeowner1);
        userManager.AddUser(homeowner2);
        userManager.AddUser(homeowner3);
        // Create test admin using AdminFactory
        Admin admin1 = new AdminFactory().CreateUser("admin", "admin123", "012345678901");
        
        userManager.AddUser(admin1);
        System.out.println("Test users created!");
        
        // Homeowner 1 rooms
        homeowner1.CreateRoomListing("City Centre", 450.0, true, true, "Cozy single room near shops");
        homeowner1.CreateRoomListing("University Area", 550.0, true, false, "Spacious double room, 5 min walk to campus");
        homeowner1.CreateRoomListing("City Centre", 600.0, true, true, "Modern ensuite room with desk");

        // Homeowner 2 rooms
        homeowner2.CreateRoomListing("Downtown", 650.0, true, true, "Luxury studio apartment");
        homeowner2.CreateRoomListing("Suburbs", 400.0, true, false, "Quiet single room in residential area");
        homeowner2.CreateRoomListing("University Area", 500.0, false, true, "Premium room - currently occupied");
        homeowner2.CreateRoomListing("City Centre", 700.0, true, true, "Penthouse room with city views");

        //homeowner 3 rooms
        homeowner3.CreateRoomListing("Downtown", 480.0, true, false, "Affordable single room");
        homeowner3.CreateRoomListing("Suburbs", 520.0, true, true,  "Comfortable double room with parking");
        homeowner3.CreateRoomListing("University Area", 580.0, true, true, "Ensuite room close to campus"); 
        System.out.println("Test rooms created!");
        
        // Create test bookings
        student1.MakeBooking(homeowner1.GetListedRooms().get(0));
        student2.MakeBooking(homeowner1.GetListedRooms().get(1));
        student3.MakeBooking(homeowner2.GetListedRooms().get(0));
        student4.MakeBooking(homeowner2.GetListedRooms().get(3));
        student5.MakeBooking(homeowner3.GetListedRooms().get(1));

        System.out.println("Test data initialized successfully!");
        System.out.println("Students: " + userManager.GetAllStudents().size());
        System.out.println("Homeowners: " + userManager.GetAllHomeowners().size());
        System.out.println("Admins: " + userManager.GetAllAdmins().size());
        System.out.println("Rooms: " + roomManager.GetAllRooms().size());
        System.out.println("Bookings: " + bookingManager.GetAllBookings().size());
    }
    
    public static void main(String[] args) {
        UI mainUI = new UI();
        mainUI.initialise();
        if (UserManager.GetInstance().GetAllUsers().isEmpty()) {
            InitialiseTestData();
        }
        mainUI.start();
    }
}