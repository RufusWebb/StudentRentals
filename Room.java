import java.util.UUID;

public class Room {

    // attributes for Room
    private String roomID;
    private String OwnerID;
    private String location;
    private double Rent;
    private boolean isAvailable;
    private boolean isFurnished;
    private String Description;

    // constructor
    public Room(String ownerID, String location, double rent, boolean isAvailable, boolean isFurnished, String description) {
        this.roomID = GenerateRoomID();
        this.OwnerID = ownerID;
        this.location = location;
        this.Rent = rent;
        this.isAvailable = isAvailable;
        this.isFurnished = isFurnished;
        this.Description = description;
    }

    // room specific methods
    private static String GenerateRoomID(){
        return "R-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    //getters and setters
    public String getRoomID() {
        return roomID;
    }
    public String getOwnerID() {
        return OwnerID;
    }
    public String getLocation() {
        return location;
    }
    public double getRent() {
        return Rent;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public boolean isFurnished() {
        return isFurnished;
    }
    public String getDescription() {
        return Description;
    }

    // package private methods, only homeowner can modify room details
    void setLocation(String location) {
        this.location = location;
    }
    void setRent(double rent) {
        this.Rent = rent;
    }
    void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    void setFurnishing(boolean isFurnished) {
        this.isFurnished = isFurnished;
    }
    void setDescription(String description) {
        this.Description = description;
    }
}
