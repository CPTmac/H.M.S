package models;

public class Appointment {

    // رقم الموعد
    private String appointmentId;

    // بيانات المريض
    private Patient patient;

    // بيانات الدكتور
    private Doctor doctor;

    // تاريخ الموعد
    private String date;

    // وقت الموعد
    private String time;

    // Constructor
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
    }

    // Getter للـ Appointment ID
    public String getAppointmentId() {
        return appointmentId;
    }

    // Setter للـ Appointment ID
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    // Getter للمريض
    public Patient getPatient() {
        return patient;
    }

    // Setter للمريض
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter للدكتور
    public Doctor getDoctor() {
        return doctor;
    }

    // Setter للدكتور
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    // Getter للتاريخ
    public String getDate() {
        return date;
    }

    // Setter للتاريخ
    public void setDate(String date) {
        this.date = date;
    }

    // Getter للوقت
    public String getTime() {
        return time;
    }

    // Setter للوقت
    public void setTime(String time) {
        this.time = time;
    }

    // عرض بيانات الموعد
    @Override
    public String toString() {

        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}