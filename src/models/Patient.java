package models;

public class Patient extends Person {

    private String insuranceInfo;
    private String medicalHistory;
    private MedicalRecord medicalRecord;

    // Constructor نظيف ومباشر
    public Patient(String id, String name, int age, String gender, MedicalRecord medicalRecord) {

        super(id, name, age, gender);

        this.medicalRecord = medicalRecord;
        this.insuranceInfo = "None";
        this.medicalHistory = "None";
    }

    // Getters / Setters

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                '}';
    }
}