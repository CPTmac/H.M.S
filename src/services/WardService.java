package services;

import models.Room;
import models.Ward;

import java.util.ArrayList;
import java.util.List;

public class WardService {

    // ليستة العنابر
    private final ArrayList<Ward> wards;

    // Constructor
    public WardService() {

        wards = new ArrayList<>();
    }

    /*
     * =========================
     * Add Ward
     * =========================
     */

    public void addWard(Ward ward) {

        if (ward == null) {

            System.out.println("Ward cannot be null.");

            return;
        }

        wards.add(ward);

        System.out.println("Ward added successfully.");
    }

    /*
     * =========================
     * Add Room To Ward
     * =========================
     */

    public boolean addRoomToWard(
            String wardName,
            Room room
    ) {

        Ward ward =
                findWardByName(wardName);

        // التأكد إن العنبر موجود
        if (ward == null) {

            System.out.println("Ward not found.");

            return false;
        }

        // إضافة الغرفة
        ward.getRooms().add(room);

        System.out.println(
                "Room added successfully."
        );

        return true;
    }

    /*
     * =========================
     * Show Available Rooms
     * =========================
     */

    public void showAvailableRooms(
            String wardName
    ) {

        Ward ward =
                findWardByName(wardName);

        if (ward == null) {

            System.out.println("Ward not found.");

            return;
        }

        System.out.println(
                "Available Rooms in "
                        + ward.getWardName()
        );

        for (Room room : ward.getRooms()) {

            if (!room.isOccupied()) {

                System.out.println(
                        "Room "
                                + room.getRoomNumber()
                );
            }
        }
    }

    /*
     * =========================
     * Find Ward
     * =========================
     */

    public Ward findWardByName(
            String wardName
    ) {

        for (Ward ward : wards) {

            if (ward.getWardName()
                    .equalsIgnoreCase(wardName)) {

                return ward;
            }
        }

        return null;
    }

    /*
     * =========================
     * Get All Wards
     * =========================
     */

    public List<Ward> getAllWards() {

        return new ArrayList<>(wards);
    }

    /*
     * =========================
     * Display Wards
     * =========================
     */

    public void displayWards() {

        for (Ward ward : wards) {

            System.out.println(ward);

            System.out.println("----------------");
        }
    }

    /*
     * =========================
     * Remove Ward
     * =========================
     */

    public boolean removeWard(
            String wardName
    ) {

        Ward ward =
                findWardByName(wardName);

        if (ward == null) {

            System.out.println("Ward not found.");

            return false;
        }

        wards.remove(ward);

        System.out.println(
                "Ward removed successfully."
        );

        return true;
    }
}