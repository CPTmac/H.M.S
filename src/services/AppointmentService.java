package services;

import java.util.ArrayList;
import java.util.List;
import models.Appointment;
import models.Doctor;
import models.Patient;

public class AppointmentService {

    private final ArrayList<Appointment> appointments;
    private final ArrayList<Doctor> doctors;
    private final ArrayList<Patient> patients;

    public AppointmentService() {

        appointments = new ArrayList<>();
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
    }

    // =========================
    // DOCTORS
    // =========================
    public void addDoctor(Doctor doctor) {

        if (doctor == null) return;

        doctors.add(doctor);
    }

    // =========================
    // PATIENTS
    // =========================
    public void addPatient(Patient patient) {

        if (patient == null) return;

        patients.add(patient);
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    // =========================
    // SEARCH PATIENT BY ID (FIXED)
    // =========================
    public Patient findPatientById(String id) {

        for (Patient p : patients) {

            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // =========================
    // BOOK APPOINTMENT (UPDATED)
    // =========================
    public boolean bookAppointment(String appointmentId,
                                   String patientId,
                                   Doctor doctor,
                                   String date,
                                   String time,
                                   String diagnosis,
                                   String room,
                                   String labTests,
                                   double totalDue) {

        Patient patient = findPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return false;
        }

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return false;
        }

        // check conflict
        for (Appointment a : appointments) {

            boolean sameDoctor =
                    a.getDoctor().getId().equalsIgnoreCase(doctor.getId());

            boolean sameDate =
                    a.getDate().equalsIgnoreCase(date);

            boolean sameTime =
                    a.getTime().equalsIgnoreCase(time);

            if (sameDoctor && sameDate && sameTime) {
                System.out.println("Doctor already booked at this time.");
                return false;
            }
        }

        Appointment appointment = new Appointment(
                appointmentId,
                patient,
                doctor,
                date,
                time,
                diagnosis,
                room,
                labTests,
                totalDue
        );

        appointments.add(appointment);

        System.out.println("Appointment booked successfully.");
        return true;
    }

    // =========================
    // REMOVE FIXED (safe)
    // =========================
    public boolean removeAppointment(String appointmentId) {

        Appointment target = null;

        for (Appointment a : appointments) {

            if (a.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                target = a;
                break;
            }
        }

        if (target != null) {

            appointments.remove(target);
            System.out.println("Appointment removed successfully.");
            return true;
        }

        System.out.println("Appointment not found.");
        return false;
    }

    // =========================
    // FIND APPOINTMENT
    // =========================
    public Appointment findAppointmentById(String appointmentId) {

        for (Appointment a : appointments) {

            if (a.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                return a;
            }
        }

        return null;
    }

    // =========================
    // DISPLAY
    // =========================
    public void displayAppointments() {

        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }
}