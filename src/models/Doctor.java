package models;

public class Doctor extends Person {
    private String id;
    private String name;
    private String specialization;
    private boolean available;

    public Doctor(String id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
        this.available = true; // متاح بشكل افتراضي
    }

    // Getters and Setters (Encapsulation)
    
    public String getDoctorId() { return id; }
    public String getDoctorName() { return name; }
    public String getSpecialization() { return specialization; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " (" + specialization + ")";
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}