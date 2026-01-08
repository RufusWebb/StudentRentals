import java.util.List;
import java.util.Scanner;

public class UI {
    // main menu
    private static UserManager userManager;
    private static RoomManager roomManager;
    private static BookingManager bookingManager;
    private static Session session;
    private static Scanner scanner;
    
    
    // initialise method to set up scanner and managers
    public void initialise(){
        scanner = new Scanner(System.in);
        userManager = UserManager.GetInstance();
        roomManager = RoomManager.GetInstance();
        bookingManager = BookingManager.GetInstance();
        session = Session.GetInstance();
        System.out.println("System intialised");
    }
    
    // start screen methods
    public void start(){
        // loop to keep app running
        boolean running = true;
        while (running) {
            ShowMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            // take user input and show corresponding screen
            switch (choice){
                case 1:
                    HandleLogin();
                    if (session.GetInstance().GetCurrentUser() != null){
                        UserMenu();
                    }
                    break;
                case 2:
                    HandleRegistration();
                    break;
                case 3:
                    System.out.println("Thank you for using Student Rentals. Goodbye");
                    running = false;
                    break;
                case 4:
                    ShowAllUsersOnScreen();
                    break;
                default:
                    System.out.println("Invalid Choice. Please enter an integer between 1 and 4.");
            }
        }
    }

        // display main menu
        public void ShowMainMenu(){
                // display main menu
                System.out.println("\n|| WELCOME TO STUDENT RENTALS ||\n" + "Enter 1 to 3 to continue to the corresponding pages.\n");
                System.out.println("1. Log In\n2. Register Account\n3. Exit\n4. Show all Users for testing\n");
                System.out.println("Please Enter your choice.");
            }

        // User login method
        public void HandleLogin(){
            System.out.println("|| Login or return to Home menu (enter 1) ||");
            // allow users to enter username and passwords
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            if (username.equalsIgnoreCase("1")){
                return;
            }
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            // check if username and password are correct
            boolean isAuthenticated = userManager.AuthenticateUser(username, password);
            // if correct details
            User user = userManager.FindByUsername(username);
            if (isAuthenticated){
                System.out.println("Login Succesful! Welcome, " + username);
                Session.GetInstance().login(user);
            }
            // incorrect details
            else {
                System.out.println("Invalid Username or password. Please try again.");
            }
        }
        
        // User Registration method
        public void HandleRegistration() {
            // display options
            System.out.println("|| Register new account ||");
            System.out.println("Select account type:");
            System.out.println("1. Student");
            System.out.println("2. Homeowner");
            // get inputs
            int UserChoice = scanner.nextInt();
            scanner.nextLine(); 
            //get username
            System.out.println("Enter username: ");
            String DesiredUsername = scanner.nextLine();

            // check if username is taken
            if (userManager.UserExists(DesiredUsername)){
                System.out.println("Username taken please enter a different username.");
                return;
            }
            // get password
            System.out.println("Enter password: ");
            String DesiredPassword = scanner.nextLine();

            // get contact number
            System.out.println("Enter contact number: ");
            String ContactNumber = scanner.nextLine();

            User NewUser = null;
            switch (UserChoice){
                case 1:
                    NewUser = new StudentFactory().CreateUser(DesiredUsername, DesiredPassword, ContactNumber);
                    break;
                case 2:
                    NewUser = new HomeownerFactory().CreateUser(DesiredUsername, DesiredPassword, ContactNumber);
                default:
                    System.out.println("Invalid account type.");
                    return;
            }
            // add a user to user manager if valid user created 
            if (NewUser != null){
                userManager.AddUser(NewUser);
                System.out.println("New account created");
            }
        }

        //show all users for debugging
        public void ShowAllUsersOnScreen(){
            userManager.GetAllUsers();
            System.out.println("All users in system:");
            for (User user : userManager.GetAllUsers()){
                System.out.println("Username: " + user.getUsername() + ", Type: " + user.getUserType());
            }
            PauseForUser();
        }

    // main screen methods

    public void UserMenu(){
        User CurrentUser = session.GetInstance().GetCurrentUser();
        boolean LoggedIn = true;
        while (LoggedIn){
            ShowCorrectMenuForUserType(CurrentUser);
            //get user choice
            int choice = scanner.nextInt();
            scanner.nextLine();
            // check which type of user is logging out
            if (CurrentUser.getUserType().equals("Student")) {
                LoggedIn = HandleStudentChoice(choice);
            } else if (CurrentUser.getUserType().equals("Homeowner")) {
                LoggedIn = HandleHomeownerChoice(choice);
            } else if (CurrentUser.getUserType().equals("Admin")) {
                LoggedIn = HandleAdminChoice(choice);
            }
        }
    }

public void ShowCorrectMenuForUserType(User user){
        if (user.getUserType().equals("Student")) {
            ShowStudentMenu();
        } else if (user.getUserType().equals("Homeowner")) {
            ShowHomeownerMenu();
        } else if (user.getUserType().equals("Admin")) {
            ShowAdminMenu();
        }
    }

    // methods to handle user menu choices
    // handle student menu choices
    private boolean HandleStudentChoice(int choice) {
    switch (choice){
        case 1:
            SearchRooms();
            break;
        case 2:
            ViewStudentBookings();
            break;
        case 3:
            BookRoom();
            break;
        case 4:
            HandleLogout();
            return false; 
        default: 
            System.out.println("Invalid Choice. Please enter an integer between 1 and 4.");
        }
    return true; 
    }

    // handle Homeowner menu choices
    private boolean HandleHomeownerChoice(int choice) {
        switch (choice){
            case 1:
                ViewMyRooms();
                break;
            case 2:
                ViewHomeownerBookings();
                break;
            case 3:
                ManageBookings();
                break;
            case 4:
                AddRoom();
                break;
            case 5:
                HandleLogout();
                return false;
            default:
                System.out.println("Invalid Choice. Please enter an integer between 1 and 4.");
        }
        return true;
    }

    //handle admin menu choices
    private boolean HandleAdminChoice(int choice) {
        switch (choice){
            case 1:
                ViewAllUsers();
                break;
            case 2:
                ViewAllBookings();
                break;
            case 3:
                ViewAllRooms();
                break;
            case 4:
                GenerateSystemReport();
                return false;
            default:
                System.out.println("Invalid Choice. Please enter an integer between 1 and 4.");
        }
        return true;
    }

    // specific user menus methods
    // student menu
    public void ShowStudentMenu(){
        System.out.println("\nStudent Menu:\n1. Search Rooms\n2. View Bookings\n3. book Room\n4. Logout");
        System.out.println("Enter Choice: ");
    }

    //specific student menu methods
    // student search rooms method
    public void SearchRooms(){
        System.out.println("Enter Search criteria or press enter to skip criteria:");
        // get search criteria from user
        // get minimum rent
        System.out.println("set min rent (press Enter to skip):");
        String minRentInput = scanner.nextLine();
        Double minRent = minRentInput.isEmpty() ? null : Double.parseDouble(minRentInput);
        // get maximum rent
        System.out.println("set max rent (press Enter to skip):");
        String maxRentInput = scanner.nextLine();
        Double maxRent = maxRentInput.isEmpty() ? null : Double.parseDouble(maxRentInput);
        // get furnished
        System.out.println("furnished? (true/false, press Enter to skip):");
        String furnishedInput = scanner.nextLine();
        Boolean isFurnished = furnishedInput.isEmpty() ? null : Boolean.parseBoolean(furnishedInput);
        // get available
        System.out.println("available? (true/false, press Enter to skip):");
        String availableInput = scanner.nextLine();
        Boolean isAvailable = availableInput.isEmpty() ? null : Boolean.parseBoolean(availableInput);
        
        //use student method to set search criteria
        Student currentStudent = (Student) session.GetInstance().GetCurrentUser();
        currentStudent.SetSearchCriteria(minRent, maxRent, isFurnished, isAvailable);
        System.out.println("Searching Rooms...");
        SearchStrategy searchStrategy = new SearchStrategy();
        // get all rooms from room manager
        List<Room> allRooms = roomManager.GetAllRooms();
        // call search strategy with user search criteria
        List<Room> matchedRooms = searchStrategy.search(allRooms, currentStudent.getSearchCriteria());

        if (matchedRooms.isEmpty()){
            System.out.println("No rooms match your criteria");
            PauseForUser();
            return;
        }

        //display matching rooms if exists
        System.out.println("|| Available Rooms ||");
        for (Room room : matchedRooms){
            if (room.isAvailable()){

            System.out.println("\nRoom ID: " + room.getRoomID() + 
                            ", Location: " + room.getLocation() + 
                            ", Rent per month: " + room.getRent() + 
                            ", Furnished: " + room.isFurnished() + 
                            "Description: " + room.getDescription());
            }
        }
        // allow users to book a room
        System.out.println("Would you like to request one of these rooms? (1 for Yes, 2 for No)");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1){
            System.out.println("Enter the Room ID of the Room you would like to request: ");
            String DesiredRoomID = scanner.nextLine();
            boolean roomfound = false;
            for (Room matchroom : matchedRooms){
                if (matchroom.getRoomID().equalsIgnoreCase(DesiredRoomID)){
                    currentStudent.MakeBooking(matchroom);
                    roomfound = true;
                    break;
                }
            }
            if (!roomfound){
                System.out.println("Room ID not found");
            }
            else{
                System.out.println("Returning to Menu...");
            }
            PauseForUser();
        }
    }

    //student book room method
    public void BookRoom(){
        Student currentStudent = (Student) session.GetInstance().GetCurrentUser();
        System.out.println("Enter Room ID to request:");
        String roomID = scanner.nextLine();
        currentStudent.MakeBooking(roomManager.FindRoomByID(roomID));
        PauseForUser();
    }

    // view all student bookings method
    public void ViewStudentBookings(){
        System.out.println("\nViewing Student Bookings...\n");
        System.out.println("Your Bookings:");
        User currentUser = session.GetInstance().GetCurrentUser();
        Student student = (Student) currentUser;
        List<Booking> StudentBookings = student.StudentBookings();
        for (Booking booking : StudentBookings){
            System.out.println(booking.toString());
        }
        PauseForUser();
    } 

    public void ShowHomeownerMenu(){
        System.out.println("\nHomeowner Menu:\n1. View all my rooms\n2. View all booking requests\n3. Manage pending bookings\n4. Add room\n5. Logout");
        System.out.println("Enter Choice: ");
    }
    // homeowner menu methods
    // view homeowner rooms method
    public void ViewMyRooms(){
        System.out.println("\nViewing My Rooms...\n");
        User currentUser = session.GetInstance().GetCurrentUser();
        Homeowner homeowner = (Homeowner) currentUser;
        homeowner.DisplayAllRooms();
        PauseForUser();
    }
    // view homeowner bookings method
    public void ViewHomeownerBookings(){
        System.out.println("\nViewing Homeowner Pending Bookings...\n");
        User currentUser = session.GetInstance().GetCurrentUser();
        Homeowner homeowner = (Homeowner) currentUser;
        homeowner.DisplayAllBookings();
        PauseForUser();
        
    }
    // add a room method
    public void AddRoom(){
        // get user
        User currentUser = session.GetInstance().GetCurrentUser();
        Homeowner homeowner = (Homeowner) currentUser;
        System.out.println("Adding a new Room...");
        System.out.println("Enter new room details:");
        // get room details from homeowner
        //get location
        System.out.println("Enter location: ");
        String location = scanner.nextLine();
        // get rent
        System.out.println("set monthly rent: ");
        String RentInput = scanner.nextLine();
        Double Rent = RentInput.isEmpty() ? null : Double.parseDouble(RentInput);
        // get furnished
        System.out.println("furnished? (true/false):");
        String furnishedInput = scanner.nextLine();
        Boolean isFurnished = furnishedInput.isEmpty() ? null : Boolean.parseBoolean(furnishedInput);
        // get a description
        System.out.println("Enter a description:");
        String Description = scanner.nextLine();
        //create new room object
        homeowner.CreateRoomListing(location, Rent, true, isFurnished, Description);
        System.out.println("New Room Created!");
        PauseForUser();
    }

    // Manage booking method
    public void ManageBookings(){
        User currentUser = session.GetInstance().GetCurrentUser();
        Homeowner homeowner = (Homeowner) currentUser;
        BookingManager bookingManager = BookingManager.GetInstance();
        // display all bookings for homeowner
        System.out.println("\nAll Bookings for your rooms:");
        homeowner.DisplayAllBookings();
        //show only pending bookings
        System.out.println("\nPending Bookings:\n");
        homeowner.DisplayAllPendingBookings();
        // allow homeowner to accept or reject bookings
        System.out.println("Enter Booking ID to manage or press Enter to return to menu:");
        String bookingID = scanner.nextLine();
        if (bookingID.isEmpty()){
            return;
        }
        Booking bookingToManage = bookingManager.GetBookingByID(bookingID);
        if (bookingToManage == null){
            System.out.println("Booking ID not found.");
            return;
        }
        System.out.println("Enter 1 to Accept or 2 to Reject the booking:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                homeowner.ConfirmBooking(bookingToManage);
                System.out.println("Booking Accepted.");
                break;
            case 2:
                homeowner.RejectBooking(bookingToManage);
                System.out.println("Booking Rejected.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // admin menu method
    public void ShowAdminMenu(){
        System.out.println("\nAdmin Menu:\n1. View all Users\n2. View all Bookings\n3. View all rooms\n4. Generate Report\n5. Logout");
    }

    // admin specific methods
    // view all Rooms
    public void ViewAllRooms() {
        Admin currentAdmin = (Admin) Session.GetInstance().GetCurrentUser();
        currentAdmin.ViewAllRooms();
        PauseForUser();
    }
    // view all Users
    public void ViewAllUsers() {
        List<User> allUsers = UserManager.GetInstance().GetAllUsers();
        Admin currentAdmin = (Admin) Session.GetInstance().GetCurrentUser();
        System.out.println("\n|| All Users in System ||");
        currentAdmin.ViewAllUsers();
        PauseForUser();
    }

    // generate system report
    public void GenerateSystemReport() {
        Admin currentAdmin = (Admin) Session.GetInstance().GetCurrentUser();
        currentAdmin.GenerateSystemReport();
        PauseForUser();
    }

    // delete user method
    public void DeleteUser(){
        Admin currentAdmin = (Admin) Session.GetInstance().GetCurrentUser();
        System.out.println("Enter username of user to delete:");
        String username = scanner.nextLine();
        currentAdmin.DeleteUser(username);
        PauseForUser();
    }

    //view all bookings method
    public void ViewAllBookings() {
        Admin currentAdmin = (Admin) Session.GetInstance().GetCurrentUser();
        currentAdmin.ViewAllBookings();
        PauseForUser();
    }

    // logout method
    public void HandleLogout(){
        System.out.println("Logging out...");
        Session.GetInstance().logout();
        System.out.println("Logged out successfully.");
    }

    //pause function
    private void PauseForUser(){
        System.out.println("\nPress Enter To Continue...");
        scanner.nextLine();
    }
}

