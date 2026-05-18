package services;

import java.util.ArrayList;
import java.util.List;
import models.Appointment;
import models.Doctor;
import models.Patient;

public class AppointmentService {

    // ليستة المواعيد
    private final ArrayList<Appointment> appointments;

    // ليستة الدكاترة
    private final ArrayList<Doctor> doctors;

    // ليستة المرضى
    private final ArrayList<Patient> patients;

    // الكونستركتور
    public AppointmentService() {

        appointments = new ArrayList<>();
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
    }

    // إضافة دكتور
    public void addDoctor(Doctor doctor) {

        if (doctor == null) {

            System.out.println("Doctor cannot be null.");

            return;
        }

        doctors.add(doctor);

        System.out.println("Doctor added successfully.");
    }

    // إضافة مريض
    public void addPatient(Patient patient) {

        if (patient == null) {

            System.out.println("Patient cannot be null.");

            return;
        }

        patients.add(patient);

        System.out.println("Patient added successfully.");
    }

    // إرجاع كل المرضى
    public List<Patient> getPatients() {

        return new ArrayList<>(patients);
    }

    // إرجاع كل الدكاترة
    public List<Doctor> getDoctors() {

        return new ArrayList<>(doctors);
    }

    // إرجاع كل المواعيد
    public List<Appointment> getAppointments() {

        return new ArrayList<>(appointments);
    }

    // البحث عن مريض باستخدام الـ ID
    public Patient findPatientById(String patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId()
                    .equalsIgnoreCase(patientId)) {

                return patient;
            }
        }

        return null;
    }

    // حجز موعد جديد
    public boolean bookAppointment(String appointmentId,
                                   String patientId,
                                   Doctor doctor,
                                   String date,
                                   String time) {

        // البحث عن المريض
        Patient patient = findPatientById(patientId);

        // التأكد إن المريض موجود
        if (patient == null) {

            System.out.println("Patient not found.");

            return false;
        }

        // التأكد إن الدكتور موجود
        if (doctor == null) {

            System.out.println("Doctor not found.");

            return false;
        }

        // التأكد إن الدكتور مشغول
        for (Appointment appointment : appointments) {

            boolean sameDoctor =
                    appointment.getDoctor().getDoctorId()
                            .equalsIgnoreCase(doctor.getDoctorId());

            boolean sameDate =
                    appointment.getDate()
                            .equalsIgnoreCase(date);

            boolean sameTime =
                    appointment.getTime()
                            .equalsIgnoreCase(time);

            // منع تعارض المواعيد
            if (sameDoctor && sameDate && sameTime) {

                System.out.println("Doctor already has appointment at this time.");

                return false;
            }
        }

        // إنشاء الموعد
        Appointment appointment = new Appointment(
                appointmentId,
                patient,
                doctor,
                date,
                time
        );

        // إضافة الموعد
        appointments.add(appointment);

        System.out.println("Appointment booked successfully.");

        return true;
    }

    // حذف موعد
    public boolean removeAppointment(String appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId()
                    .equalsIgnoreCase(appointmentId)) {

                appointments.remove(appointment);

                System.out.println("Appointment removed successfully.");

                return true;
            }
        }

        System.out.println("Appointment not found.");

        return false;
    }

    // البحث عن موعد
    public Appointment findAppointmentById(String appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId()
                    .equalsIgnoreCase(appointmentId)) {

                return appointment;
            }
        }

        return null;
    }

    // عرض كل المواعيد
    public void displayAppointments() {

        for (Appointment appointment : appointments) {

            System.out.println(appointment);
        }
    }
}