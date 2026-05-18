package models;

public class Doctor {
    private String id;
    private String name;
    private String specialization;
    private boolean available;

    public Doctor(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.available = true; // متاح بشكل افتراضي
    }

    // Getters and Setters (Encapsulation)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " (" + specialization + ")";
    }
}