package models;

public class User {
    private String Name;
    private String Email;
    private String Password;
    private boolean IsHomeowner;
    
    //User Constructor
    public User(String Name,String Email,String Password,Boolean Homeowner){
        this.Name=Name;
        this.Email=Email;
        this.Password=Password;
        this.IsHomeowner=Homeowner;
    }
    //Name and password checker to allow login
    public boolean NamePasswordCheck(String PasswordAttempt, String NameAttempt){
        return PasswordAttempt.equals(Password)&&NameAttempt.equals(Name);
        }
    
    
    }


