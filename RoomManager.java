import java.util.ArrayList;
import java.util.List;

public class RoomManager{

    private static RoomManager instance = null;
    private List<Room> AllRooms;

    private RoomManager() {
        this.AllRooms = new ArrayList<>();
    }

    public static RoomManager GetInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }
        return instance;
    }

    public void AddRoom(Room room) {
        AllRooms.add(room);
        System.out.println("Room added to system");
    }

    public void RemoveRoom(Room room) {
        AllRooms.remove(room);
        System.out.println("Room removed from system");
    }

    // getting a copy of all rooms
    public List<Room> GetAllRooms() {
        return new ArrayList<>(AllRooms);
    }

    // getting a copy of available rooms
    public List<Room> GetAvailableRooms() {
        List<Room> AvailableRooms = new ArrayList<>();
        for (Room room : AllRooms){
            if (room.isAvailable()) {
                AvailableRooms.add(room);
            }
        }
        return AvailableRooms;
    }

    // getting rooms by max rent
    public List<Room> GetRoomsByMaxRent(double maxRent) {
        List<Room> FilteredRooms = new ArrayList<>();
        for (Room room : AllRooms){
            if (room.getRent() <= maxRent) {
                FilteredRooms.add(room);
            }
        }
        return FilteredRooms;
    }

    // getting rooms by location keyword
    public List<Room> GetRoomsByLocation(String locationKeyword) {
        List<Room> FilteredRooms = new ArrayList<>();
        for (Room room : AllRooms){
            if (room.getLocation().toLowerCase().contains(locationKeyword.toLowerCase())) {
                FilteredRooms.add(room);
            }
        }
        return FilteredRooms;
    }

    // getting room by ID
    public Room FindRoomByID(String roomID) {
        for (Room room : AllRooms){
            if (room.getRoomID().equalsIgnoreCase(roomID)) {
                return room;
            }
        }
        return null; 
    }

}