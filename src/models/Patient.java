package models;

public class Patient extends Person {

    // رقم تعريف المريض
    private String patientId;

    // معلومات التأمين
    private String insuranceInfo;

    // التاريخ المرضي
    private String medicalHistory;

    // السجل الطبي الخاص بالمريض
    private MedicalRecord medicalRecord;

    // Constructor
    public Patient(String id, String name, int age, String address, String patientId, String insuranceInfo, String medicalHistory, MedicalRecord medicalRecord) {

        // استدعاء الكونستركتور الخاص بـ Person
        super(id, name, age);

        this.patientId = patientId;
        this.insuranceInfo = insuranceInfo;
        this.medicalHistory = medicalHistory;
        this.medicalRecord = medicalRecord;
    }

    // Getter للـ Patient ID
    public String getPatientId() {
        return patientId;
    }

    // Setter للـ Patient ID
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // Getter للـ Insurance Info
    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    // Setter للـ Insurance Info
    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    // Getter للـ Medical History
    public String getMedicalHistory() {
        return medicalHistory;
    }

    // Setter للـ Medical History
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    // Getter للـ Medical Record
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    // Setter للـ Medical Record
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    // عرض بيانات المريض
    @Override
    public String toString() {

        return "Patient{" +"id='" + getId() + '\'' +", name='" + getName() + '\'' +", age=" + getAge() +", address='" +  '\'' +", patientId='" + patientId + '\'' +", insuranceInfo='" + insuranceInfo + '\'' +", medicalHistory='" + medicalHistory + '\'' +", medicalRecord=" + medicalRecord +'}';
    }
}