package models;

public class Room {

    // رقم الغرفة
    private int roomNumber;

    // هل الغرفة مشغولة؟
    private boolean occupied;

    // حالة النظافة
    private String cleaningStatus;

    // المريض الموجود بالغرفة
    private Patient patient;

    // Constructor
    public Room(int roomNumber) {

        this.roomNumber = roomNumber;

        occupied = false;

        cleaningStatus = "Clean";

        patient = null;
    }

    // Getter لرقم الغرفة
    public int getRoomNumber() {
        return roomNumber;
    }

    // Setter لرقم الغرفة
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Getter لحالة الانشغال
    public boolean isOccupied() {
        return occupied;
    }

    // Setter لحالة الانشغال
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // Getter لحالة النظافة
    public String getCleaningStatus() {
        return cleaningStatus;
    }

    // Setter لحالة النظافة
    public void setCleaningStatus(String cleaningStatus) {
        this.cleaningStatus = cleaningStatus;
    }

    // Getter للمريض
    public Patient getPatient() {
        return patient;
    }

    // Setter للمريض
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {

        return "Room{" +
                "roomNumber=" + roomNumber +
                ", occupied=" + occupied +
                ", cleaningStatus='" + cleaningStatus + '\'' +
                ", patient=" +
                (patient != null
                        ? patient.getName()
                        : "No Patient") +
                '}';
    }
}