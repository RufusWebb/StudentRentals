import java.util.ArrayList;
import java.util.List;

public class UserManager{

    private static UserManager instance = null;
    private List<User> AllUsers;

    private UserManager() {
        this.AllUsers = new ArrayList<>();
    }

    public static UserManager GetInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // method to add user
    public void AddUser(User user) {
        if (user != null && !UserExists(user.getUsername())){
            AllUsers.add(user);
            System.out.println("User added to management system");
        }
        else if (user == null) {
        System.out.println("User already exists");
        }
        else {
            System.out.println("user already exists");
        }
    }

    // method to check if user alreay in system
    public boolean UserExists(String EnteredUsername){
        for (User user : AllUsers){
            if (user.getUsername().equals(EnteredUsername)){
                return true;
            }
        }
        return false;
    }

    // getting all users
    public List<User> GetAllUsers() {
        return new ArrayList<>(AllUsers);
    }

// getting all students
public List<Student> GetAllStudents() {
    ArrayList<Student> AllStudents = new ArrayList<>();
    for (User user : AllUsers){
        if (user.getUserType().equalsIgnoreCase("Student")){
            AllStudents.add((Student) user);
        }
    }
    return AllStudents;
}

// get all homeowners
public List<Homeowner> GetAllHomeowners() {
    ArrayList<Homeowner> AllHomeowners = new ArrayList<>();
    for (User user : AllUsers){
        if (user.getUserType().equalsIgnoreCase("Homeowner")){
            AllHomeowners.add((Homeowner) user);
        }
    }
    return AllHomeowners;
}

// get all Admins
public List<Admin> GetAllAdmins() {
    ArrayList<Admin> AllAdmins = new ArrayList<>();
    for (User user : AllUsers){
        if (user.getUserType().equalsIgnoreCase("Admin")){
            AllAdmins.add((Admin) user);
        }
    }
    return AllAdmins;
}

// find user by userID as theyre unique
public User FindByUserID(String userID){
    for (User user : AllUsers){
        if (user.getUserID().equalsIgnoreCase(userID)){
            return user;
        }
    }
    return null;
}

//find user by username 
public User FindByUsername(String username){
    for (User user : AllUsers){
        if (user.getUsername().equalsIgnoreCase(username)){
            return user;
        }
    }
    return null;
}

//remove user by userID
public boolean RemoveUser(String UserID){
    User UserToRemove = FindByUserID(UserID);
    if (UserToRemove != null){
        AllUsers.remove(UserToRemove);
        System.out.println("User Removed "+ UserToRemove.getUsername());
        return true;
    }
    return false;
}

//check user log in details
public boolean AuthenticateUser(String EnteredUsername, String EnteredPassword){
    for (User user : AllUsers){
        if (user.getUsername().equals(EnteredUsername) && user.getPassword().equals(EnteredPassword)){
            return true;
            }
        }
        return false;
    }
}