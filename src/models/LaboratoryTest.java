package models;

import java.io.Serializable;

public class LaboratoryTest implements Serializable {

    private String id;

    private String patientId;

    private String doctorId;

    private String testName;

    private double cost;

    private LaboratoryTestStatus status;

    private LabResult result;

    public LaboratoryTest(String id,
                          String patientId,
                          String doctorId,
                          String testName,
                          double cost) {

        this.id = id;

        this.patientId = patientId;

        this.doctorId = doctorId;

        this.testName = testName;

        this.cost = cost;

        status = LaboratoryTestStatus.PENDING;

        result = null;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LaboratoryTestStatus getStatus() {
        return status;
    }

    public void setStatus(LaboratoryTestStatus status) {
        this.status = status;
    }

    public LabResult getResult() {
        return result;
    }

    public void setResult(LabResult result) {
        this.result = result;
    }

    @Override
    public String toString() {

        return "LaboratoryTest{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", testName='" + testName + '\'' +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }
}