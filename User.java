import java.util.UUID;

public abstract class User {

    // common attributes for all users
    private String UserID;
    private String Username;
    private String Password;
    private String UserType;
    private String ContactNumber;

    // constructor
    public User(String username, String password, String userType, String contactNumber) {
        this.UserID = CreateUserID(userType);
        this.Username = username;
        this.Password = password;
        this.UserType = userType;
        this.ContactNumber = contactNumber;
    }

    // getters
    public static String CreateUserID(String userType){
        if (userType.equals("Student")) {
            return "S-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        } else if (userType.equals("Homeowner")) {
            return "H-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        }
        return "A-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
    public String getUserID() {
        return UserID;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
    public String getUserType() {
        return UserType;
    }
    public String getContactNumber() {
        return ContactNumber;
    }
}
