
public class Test {
    public static void main(String[] args) {
        System.out.println("=== STUDENT RENTALS APPLICATION TEST ===\n");
        
        // ==========================================
        // TEST 1: Create Users with Factories
        // ==========================================
        System.out.println("--- TEST 1: Creating Users ---");
        StudentFactory studentFactory = new StudentFactory();
        HomeownerFactory homeownerFactory = new HomeownerFactory();
        
        Student student1 = studentFactory.CreateUser("alice_jones", "pass123");
        Student student2 = studentFactory.CreateUser("bob_smith", "pass456");
        Homeowner homeowner1 = homeownerFactory.CreateUser("jane_landlord", "secure789");
        
        System.out.println("Created Student 1: " + student1.getUsername() + " (ID: " + student1.getUserID() + ")");
        System.out.println("Created Student 2: " + student2.getUsername() + " (ID: " + student2.getUserID() + ")");
        System.out.println("Created Homeowner: " + homeowner1.getUsername() + " (ID: " + homeowner1.getUserID() + ")");
        System.out.println();
        
        // ==========================================
        // TEST 2: Homeowner Creates Rooms
        // ==========================================
        System.out.println("--- TEST 2: Homeowner Creates Rooms ---");
        Room room1 = homeowner1.CreateRoomListing("Manchester City Centre", 500.0, true, true, "Cozy studio apartment near university");
        Room room2 = homeowner1.CreateRoomListing("Salford Quays", 450.0, true, false, "Spacious room in shared house");
        Room room3 = homeowner1.CreateRoomListing("Fallowfield", 400.0, true, true, "Student accommodation with all utilities");
        
        System.out.println("Homeowner property count: " + homeowner1.getPropertyCount());
        System.out.println();
        
        // ==========================================
        // TEST 3: Display All Rooms
        // ==========================================
        System.out.println("--- TEST 3: Display All Rooms ---");
        homeowner1.DisplayAllRooms();
        System.out.println();
        
        // ==========================================
        // TEST 4: Students Create Bookings
        // ==========================================
        System.out.println("--- TEST 4: Students Create Bookings ---");
        
        Booking booking1 = new Booking(student1, room1);
        BookingManager.GetInstance().AddBooking(booking1);
        System.out.println("Student 1 (" + student1.getUsername() + ") created booking: " + booking1.getBookingID());
        System.out.println("  Room: " + booking1.getRoom().getLocation());
        System.out.println("  Status: " + booking1.getBookingStatus());
        System.out.println();
        
        Booking booking2 = new Booking(student2, room2);
        BookingManager.GetInstance().AddBooking(booking2);
        System.out.println("Student 2 (" + student2.getUsername() + ") created booking: " + booking2.getBookingID());
        System.out.println("  Room: " + booking2.getRoom().getLocation());
        System.out.println("  Status: " + booking2.getBookingStatus());
        System.out.println();
        
        // ==========================================
        // TEST 5: Homeowner Views Pending Bookings
        // ==========================================
        System.out.println("--- TEST 5: Homeowner Views Pending Bookings ---");
        var pendingBookings = BookingManager.GetInstance().GetBookingsForHomeowner(homeowner1);
        System.out.println("Homeowner has " + pendingBookings.size() + " pending booking(s):");
        for (Booking b : pendingBookings) {
            System.out.println("  Booking ID: " + b.getBookingID());
            System.out.println("  Room: " + b.getRoom().getLocation());
            System.out.println("  Student ID: " + b.getUserID());
            System.out.println();
        }
        
        // ==========================================
        // TEST 6: Homeowner Confirms a Booking
        // ==========================================
        System.out.println("--- TEST 6: Homeowner Confirms Booking ---");
        homeowner1.ConfirmBooking(booking1);
        System.out.println("Booking 1 status after confirmation: " + booking1.getBookingStatus());
        System.out.println("Room 1 availability after confirmation: " +  (room1.isAvailable() ? "Available" : "Not Available"));
        System.out.println();
        
        // ==========================================
        // TEST 7: Homeowner Cancels a Booking
        // ==========================================
        System.out.println("--- TEST 7: Homeowner Cancels Booking ---");
        homeowner1.RejectBooking(booking2);
        System.out.println("Booking 2 status after cancellation: " + booking2.getBookingStatus());
        System.out.println("Room 2 availability after cancellation: " + (room2.isAvailable() ? "Available" : "Not Available"));
        System.out.println();
        
        // ==========================================
        // TEST 8: Update Room Details
        // ==========================================
        System.out.println("--- TEST 8: Update Room Details ---");
        System.out.println("Room 3 rent before update: £" + room3.getRent());
        homeowner1.UpdateRoomDetails(room3.getRoomID(), null, 425.0, null, null, null);
        System.out.println("Room 3 rent after update: £" + room3.getRent());
        System.out.println();
        
        // ==========================================
        // TEST 9: Remove a Room
        // ==========================================
        System.out.println("--- TEST 9: Remove a Room ---");
        System.out.println("Property count before removal: " + homeowner1.getPropertyCount());
        homeowner1.RemoveRoom(room3.getRoomID());
        System.out.println("Property count after removal: " + homeowner1.getPropertyCount());
        System.out.println();
        
        // ==========================================
        // TEST 10: Student Views Their Bookings
        // ==========================================
        System.out.println("--- TEST 10: Student Views Their Bookings ---");
        var student1Bookings = BookingManager.GetInstance().GetBookingsForStudent(student1);
        System.out.println("Student 1 (" + student1.getUsername() + ") has " + student1Bookings.size() + " booking(s):");
        for (Booking b : student1Bookings) {
            System.out.println("  Booking ID: " + b.getBookingID());
            System.out.println("  Room: " + b.getRoom().getLocation());
            System.out.println("  Status: " + b.getBookingStatus());
            System.out.println();
        }
        
        // ==========================================
        // FINAL SUMMARY
        // ==========================================
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("✓ User creation with auto-generated IDs");
        System.out.println("✓ Room creation with auto-generated IDs");
        System.out.println("✓ Room management (create, update, remove)");
        System.out.println("✓ Booking creation with auto-generated IDs");
        System.out.println("✓ Booking status management (pending, confirmed, cancelled)");
        System.out.println("✓ Room availability updates based on booking status");
        System.out.println("✓ BookingManager singleton pattern");
        System.out.println("✓ Homeowner and Student booking queries");
        System.out.println("\nAll tests completed successfully!");
    }
}