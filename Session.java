public class Session {

    private static Session instance;
    private User CurrentUser;
    private Boolean authenticated;

    private Session() {
        this.authenticated = false;
    }
    
    public static Session GetInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Session specific methods
    // login method
    public void login(User user){
        if (user == null) {
            throw new IllegalArgumentException("User Cannot be null");
        }
        this.CurrentUser = user;
        this.authenticated = true;
    }

    //logout method
    public void logout(){
        this.CurrentUser = null;
        this.authenticated = false;
    }

    //getters
    public boolean IsAuthenticated(){
        return authenticated;
    }

    public User GetCurrentUser(){
        return CurrentUser;
    }
}
