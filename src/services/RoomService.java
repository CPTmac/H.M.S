package services;

import models.Patient;
import models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    // ليستة الغرف
    private final ArrayList<Room> rooms;

    // Constructor
    public RoomService() {

        rooms = new ArrayList<>();
    }

    /*
     * =========================
     * Add Room
     * =========================
     */

    public void addRoom(Room room) {

        if (room == null) {

            System.out.println("Room cannot be null.");

            return;
        }

        rooms.add(room);

        System.out.println("Room added successfully.");
    }

    /*
     * =========================
     * Assign Patient To Room
     * =========================
     */

    public boolean assignPatientToRoom(
            int roomNumber,
            Patient patient
    ) {

        Room room =
                findRoomByNumber(roomNumber);

        // التأكد إن الغرفة موجودة
        if (room == null) {

            System.out.println("Room not found.");

            return false;
        }

        // التأكد إن الغرفة فاضية
        if (room.isOccupied()) {

            System.out.println("Room already occupied.");

            return false;
        }

        // التأكد إن الغرفة نظيفة
        if (!room.getCleaningStatus()
                .equalsIgnoreCase("Clean")) {

            System.out.println("Room is not clean.");

            return false;
        }

        // تعيين المريض
        room.setPatient(patient);

        room.setOccupied(true);

        System.out.println(
                "Patient assigned successfully."
        );

        return true;
    }

    /*
     * =========================
     * Remove Patient
     * =========================
     */

    public boolean removePatientFromRoom(
            int roomNumber
    ) {

        Room room =
                findRoomByNumber(roomNumber);

        if (room == null) {

            System.out.println("Room not found.");

            return false;
        }

        // إزالة المريض
        room.setPatient(null);

        room.setOccupied(false);

        room.setCleaningStatus("Dirty");

        System.out.println(
                "Patient removed successfully."
        );

        return true;
    }

    /*
     * =========================
     * Clean Room
     * =========================
     */

    public boolean cleanRoom(int roomNumber) {

        Room room =
                findRoomByNumber(roomNumber);

        if (room == null) {

            System.out.println("Room not found.");

            return false;
        }

        room.setCleaningStatus("Clean");

        System.out.println(
                "Room cleaned successfully."
        );

        return true;
    }

    /*
     * =========================
     * Find Room
     * =========================
     */

    public Room findRoomByNumber(
            int roomNumber
    ) {

        for (Room room : rooms) {

            if (room.getRoomNumber()
                    == roomNumber) {

                return room;
            }
        }

        return null;
    }

    /*
     * =========================
     * Get All Rooms
     * =========================
     */

    public List<Room> getAllRooms() {

        return new ArrayList<>(rooms);
    }

    /*
     * =========================
     * Display Rooms
     * =========================
     */

    public void displayRooms() {

        for (Room room : rooms) {

            System.out.println(room);

            System.out.println("----------------");
        }
    }
}