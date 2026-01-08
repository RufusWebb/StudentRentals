import java.util.ArrayList;
import java.util.List;

public class SearchStrategy {
    public List<Room> search(List<Room> AllRooms, SearchCriteria criteria){
        List<Room> matchedRooms = new ArrayList<Room>();
        for (Room room : AllRooms){
            boolean matches = true;
            // check if max rent criteria is met
            if (criteria.GetMaxRent() != null && room.getRent() > criteria.GetMaxRent()) {
                matches = false;
            }
            // check if furnished criteria is met
            if (criteria.GetisFurnished() != null && room.isFurnished() != criteria.GetisFurnished()) {
                matches = false;
            }
            // check if availability criteria is met
            if (criteria.GetisAvailable() != null && room.isAvailable() != criteria.GetisAvailable()) {
                matches = false;
            }
            // if conditions met, add to matched rooms
            if (matches) {
                matchedRooms.add(room);
            }
        }
        return matchedRooms;
    }
}
