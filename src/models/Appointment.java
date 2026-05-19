package models;

public class Appointment {

    private String appointmentId;

    private Patient patient;
    private Doctor doctor;

    private String date;
    private String time;

    private String diagnosis;
    private String room;
    private String labTests;
    private double totalDue;

    // =========================
    // Constructor الأساسي
    // =========================
    public Appointment(String appointmentId,
                       Patient patient,
                       Doctor doctor,
                       String date,
                       String time) {

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;

        setDefaults();
    }

    // =========================
    // Constructor الموسع
    // =========================
    public Appointment(String appointmentId,
                       Patient patient,
                       Doctor doctor,
                       String date,
                       String time,
                       String diagnosis,
                       String room,
                       String labTests,
                       double totalDue) {

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;

        this.diagnosis = diagnosis;
        this.room = room;
        this.labTests = labTests;
        this.totalDue = totalDue;
    }

    // =========================
    // Default values handler
    // =========================
    private void setDefaults() {
        this.diagnosis = "";
        this.room = "";
        this.labTests = "";
        this.totalDue = 0.0;
    }

    // =========================
    // Getters / Setters
    // =========================

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getRoom() {
        return room;
    }

    public String getLabTests() {
        return labTests;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setLabTests(String labTests) {
        this.labTests = labTests;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

    // =========================
    // Safe toString (important)
    // =========================
    @Override
    public String toString() {

        String patientName = (patient != null) ? patient.getName() : "N/A";
        String doctorName = (doctor != null) ? doctor.getName() : "N/A";

        return "Appointment{" +
                "id='" + appointmentId + '\'' +
                ", patient=" + patientName +
                ", doctor=" + doctorName +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", room='" + room + '\'' +
                ", labTests='" + labTests + '\'' +
                ", totalDue=" + totalDue +
                '}';
    }
}