package models;

public class MedicalRecord {

    // بيانات السجل الطبي
    private String diagnosis;
    private String allergies;
    private String bloodType;

    // المريض المرتبط بالسجل
    private Patient patient;

    // Constructor
    public MedicalRecord(Patient patient, String diagnosis, String allergies, String bloodType) {

        this.patient = patient;
        this.diagnosis = diagnosis;
        this.allergies = allergies;
        this.bloodType = bloodType;
    }

    // Getter للمريض
    public Patient getPatient() {
        return patient;
    }

    // Setter للمريض
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getter للتشخيص
    public String getDiagnosis() {
        return diagnosis;
    }

    // Setter للتشخيص
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Getter للحساسية
    public String getAllergies() {
        return allergies;
    }

    // Setter للحساسية
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    // Getter لفصيلة الدم
    public String getBloodType() {
        return bloodType;
    }

    // Setter لفصيلة الدم
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    // عرض بيانات السجل الطبي
    @Override
    public String toString() {

        return "MedicalRecord{" + "patient=" + patient.getName() + ", diagnosis='" + diagnosis + '\'' + ", allergies='" + allergies + '\'' + ", bloodType='" + bloodType + '\'' + '}';
    }
}