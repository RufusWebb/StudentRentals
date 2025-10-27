package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private static int NextId = 1;
    private final int RoomId;
    private String StreetAddress;
    private String City;
    private String Postcode;
    private String County;
    private float Rent;
    private List<LocalDate> AvailableDateList;

    public Room(String StreetAddress, String City, String Postcode, String County, float Rent){
        this.RoomId = NextId++;
        this.StreetAddress=StreetAddress;
        this.City=City;
        this.Postcode=Postcode;
        this.Rent=Rent;
        this.AvailableDateList = new ArrayList<>();
    }

    public void AddDates(LocalDate NewDate){
        if (AvailableDateList.contains(NewDate)==false){
        this.AvailableDateList.add(NewDate);
        }
    }

    public void ChangeRent(float NewRent){
        this.Rent=NewRent;
    }

    public String DisplayRoom(){
        return "Room->"
        +"RoomID:"+RoomId
        +", address:"+StreetAddress
        +'\''
        +", price:£"+Rent
        +", Available:"+AvailableDateList;
    }

    public static void main(String[] args) {
        Room TestRoom = new Room("123 test street", "Cardiff", "CF24 4NU", "hertfordshire", 12345.69f);
        TestRoom.AddDates(LocalDate.parse("2025-03-19"));
        System.out.println(TestRoom.DisplayRoom());
    }
}
