package models;

public class Doctor extends Person {

    private String specialization;
    private boolean available;

    public Doctor(String id, String name, int age, String gender, String specialization) {

        super(id, name, age, gender);

        this.specialization = specialization;
        this.available = true;
    }

    public String getDoctorId() {
        return getId();
    }

    public String getDoctorName() {
        return getName();
    }

    public String getSpecialization() {
        return specialization;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return getName() + " (" + specialization + ")";
    }
}