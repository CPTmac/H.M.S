package models;

import java.util.ArrayList;


public class Ward {

    private String wardName;
    private ArrayList<Room> rooms;

    public Ward(String wardName) {

        this.wardName = wardName;
        rooms = new ArrayList<>();
    }

    // Add room
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Display available rooms
    public void showAvailableRooms() {

        for (Room room : rooms) {

            if (!room.isOccupied()) {

                System.out.println(
                        "Room " + room.getRoomNumber()
                );
            }
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String toString() {
        return "Ward: " + wardName + ", Rooms: " + rooms.size();
    }

}