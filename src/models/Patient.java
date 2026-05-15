package models;

public class Patient extends Person {

    // رقم تعريف المريض
    private String patientId;

    // معلومات إضافية للمريض
    private String insuranceInfo;
    private String medicalHistory;

    // Constructor
    public Patient(String id, String name, int age, String address, String patientId, String insuranceInfo, String medicalHistory) {

        // استدعاء الكونستركتور الخاص بـ Person
        super(id, name, age, address);

        this.patientId = patientId;
        this.insuranceInfo = insuranceInfo;
        this.medicalHistory = medicalHistory;
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

    // عرض بيانات المريض
    @Override
    public String toString() {

        return "Patient{" + "id='" + getId() + '\'' + ", name='" + getName() + '\'' + ", age=" + getAge() + ", address='" + getAddress() + '\'' + ", patientId='" + patientId + '\'' + ", insuranceInfo='" + insuranceInfo + '\'' + ", medicalHistory='" + medicalHistory + '\'' + '}';
    }
}