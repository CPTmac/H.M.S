package hospital.service;

import hospital.model.Appointment;
import hospital.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public ScheduleService() {
        // بيانات تجريبية لإظهارها في الـ GUI
        doctors.add(new Doctor("1", "د. أحمد علي", "باطنة"));
        doctors.add(new Doctor("2", "د. سارة محمود", "أطفال"));
        doctors.add(new Doctor("3", "د. محمد حسن", "قلب"));
    }

    public List<Doctor> getDoctors() { return doctors; }
    public List<Appointment> getAppointments() { return appointments; }

    // إضافة موعد جديد مع التحقق من التعارض (Validation & Availability)
    public boolean bookAppointment(String patient, Doctor doctor, String date, String time) {
        if (patient == null || patient.isEmpty() || date == null || date.isEmpty() || time == null || time.isEmpty() || doctor == null) {
            return false; // Validation خطأ في المدخلات
        }

        // منع المواعيد المتكررة لنفس الدكتور في نفس الوقت والتاريخ
        for (Appointment app : appointments) {
            if (app.getDoctor().getId().equals(doctor.getId()) && 
                app.getDate().equals(date) && 
                app.getTime().equals(time)) {
                return false; // الدكتور مشغول في هذا الوقت
            }
        }

        // إنشاء الحجز وإضافته
        Appointment newAppointment = new Appointment(patient, doctor, date, time);
        appointments.add(newAppointment);
        return true;
    }
}
