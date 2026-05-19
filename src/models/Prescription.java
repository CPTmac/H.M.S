package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Prescription implements Serializable {

    private String id;

    private Patient patient;

    private Doctor doctor;

    private ArrayList<Medication> medications;

    private String createdAt;

    private boolean sentToPharmacy;

    public Prescription(String id,
                        Patient patient,
                        Doctor doctor,
                        ArrayList<Medication> medications,
                        String createdAt) {

        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medications = medications;
        this.createdAt = createdAt;

        sentToPharmacy = false;
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isSentToPharmacy() {
        return sentToPharmacy;
    }

    public void setSentToPharmacy(boolean sentToPharmacy) {
        this.sentToPharmacy = sentToPharmacy;
    }

    @Override
    public String toString() {

        return "Prescription " + id +
                " | doctor: " + doctor.getName() +
                " | meds: " + medications.size() +
                " | time " + createdAt;
    }
}