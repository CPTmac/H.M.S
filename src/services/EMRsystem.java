package services;

import java.util.ArrayList;
import java.util.List;

import models.Patient;
import models.MedicalRecord;

public class EMRSystem {

    // ليستة لتخزين كل المرضى
    private ArrayList<Patient> patients;

    // الكونستركتور
    public EMRSystem() {
        patients = new ArrayList<>();
    }

    // إضافة مريض جديد
    public void addPatient(Patient patient) {

        // التأكد إن المريض مش فارغ
        if (patient == null) {

            System.out.println("Patient cannot be null.");

            return;
        }

        // التأكد إن الـ ID مش متكرر
        if (findPatientById(patient.getPatientId()) != null) {

            System.out.println(
                "Patient with ID " + patient.getPatientId() + " already exists."
            );

            return;
        }

        // إضافة المريض
        patients.add(patient);

        System.out.println("Patient added successfully.");
    }

    // حذف مريض باستخدام الـ ID
    public boolean removePatient(String patientId) {

        // البحث عن المريض
        Patient patient = findPatientById(patientId);

        // لو المريض موجود يتم حذفه
        if (patient != null) {

            patients.remove(patient);

            System.out.println("Patient removed successfully.");

            return true;
        }

        // لو المريض غير موجود
        System.out.println("Patient not found.");

        return false;
    }

    // البحث عن مريض باستخدام الـ ID
    public Patient findPatientById(String patientId) {

        // المرور على كل المرضى
        for (Patient patient : patients) {

            // مقارنة الـ IDs
            if (patient.getPatientId().equalsIgnoreCase(patientId)) {

                return patient;
            }
        }

        // لو المريض غير موجود
        return null;
    }

    // تعديل بيانات مريض
    public boolean updatePatient(Patient updatedPatient) {

        // المرور على الليستة
        for (int i = 0; i < patients.size(); i++) {

            // البحث باستخدام الـ ID
            if (patients.get(i)
                    .getPatientId()
                    .equalsIgnoreCase(updatedPatient.getPatientId())) {

                // تحديث بيانات المريض
                patients.set(i, updatedPatient);

                System.out.println("Patient updated successfully.");

                return true;
            }
        }

        // لو المريض غير موجود
        System.out.println("Patient not found.");

        return false;
    }

    // إرجاع كل المرضى
    public List<Patient> getAllPatients() {

        // إرجاع نسخة من الليستة للحماية
        return new ArrayList<>(patients);
    }

    // عرض السجل الطبي لمريض
    public void displayMedicalRecord(String patientId) {

        // البحث عن المريض
        Patient patient = findPatientById(patientId);

        if (patient != null) {

            MedicalRecord medicalRecord = patient.getMedicalRecord();

            System.out.println(medicalRecord);

        } else {

            System.out.println("Patient not found.");
        }
    }

    // تعديل التشخيص
    public void updateDiagnosis(String patientId, String newDiagnosis) {

        Patient patient = findPatientById(patientId);

        if (patient != null) {

            patient.getMedicalRecord().setDiagnosis(newDiagnosis);

            System.out.println("Diagnosis updated successfully.");

        } else {

            System.out.println("Patient not found.");
        }
    }

    // تعديل الحساسية
    public void updateAllergies(String patientId, String newAllergies) {

        Patient patient = findPatientById(patientId);

        if (patient != null) {

            patient.getMedicalRecord().setAllergies(newAllergies);

            System.out.println("Allergies updated successfully.");

        } else {

            System.out.println("Patient not found.");
        }
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}