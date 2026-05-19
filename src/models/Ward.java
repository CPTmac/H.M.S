package models;

import java.util.ArrayList;

public class Ward {

    // اسم العنبر
    private String wardName;

    // ليستة الغرف
    private ArrayList<Room> rooms;

    // Constructor
    public Ward(String wardName,
                ArrayList<Room> rooms) {

        this.wardName = wardName;

        this.rooms = rooms;
    }

    // Getter لاسم العنبر
    public String getWardName() {
        return wardName;
    }

    // Setter لاسم العنبر
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    // Getter للغرف
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // Setter للغرف
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {

        return "Ward{" +
                "wardName='" + wardName + '\'' +
                ", rooms=" + rooms.size() +
                '}';
    }
}