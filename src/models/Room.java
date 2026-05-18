package models;

public class Room {

    private int roomNumber;
    private boolean isOccupied;
    private String cleaningStatus;
    private Patient patient; // المريض الموجود بالغرفة

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.cleaningStatus = "Clean";
    }

    // Assign patient
    public boolean assignPatient(Patient patient) {

        if (!isOccupied && cleaningStatus.equals("Clean")) {

            this.patient = patient;
            isOccupied = true;

            return true;
        }

        return false;
    }

    // Remove patient
    public void removePatient() {

        this.patient = null;
        isOccupied = false;
        cleaningStatus = "Dirty";
    }

    // Getters & Setters

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getCleaningStatus() {
        return cleaningStatus;
    }

    public void setCleaningStatus(String cleaningStatus) {
        this.cleaningStatus = cleaningStatus;
    }

    public Patient getPatient() {
        return patient;
    }
}