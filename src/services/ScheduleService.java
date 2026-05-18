package services;

import java.util.ArrayList;
import java.util.List;
import models.Appointment;
import models.Doctor;
import models.Patient;

public class ScheduleService {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    public ScheduleService() {
        // بيانات تجريبية لإظهارها في الـ GUI
        
    }

    public List<Doctor> getDoctors() { return doctors; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<Patient> getPatients() {return patients;}

    // إضافة موعد جديد مع التحقق من التعارض (Validation & Availability)
    public boolean bookAppointment(String id, Patient patient, Doctor doctor, String date, String time) {

        // منع المواعيد المتكررة لنفس الدكتور في نفس الوقت والتاريخ
        for (Appointment app : appointments) {
            if (app.getDoctor().getId().equals(doctor.getId()) && 
                app.getDate().equals(date) && 
                app.getTime().equals(time)) {
                return false; // الدكتور مشغول في هذا الوقت
            }
        }

        // إنشاء الحجز وإضافته
        Appointment newAppointment = new Appointment(id, patient, doctor, date, time);
        appointments.add(newAppointment);
        return true;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
