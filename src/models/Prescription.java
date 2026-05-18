package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/** Prescription has an ArrayList of Medication (composition) */
public class Prescription implements Serializable {

    private String id;
    private Patient patient;
    private Doctor doctor;
    private ArrayList<Medication> medications;
    private String createdAt;
    private boolean sentToPharmacy;

    public Prescription(Patient patient, Doctor doctor) {
        this.id = UUID.randomUUID().toString();
        this.patient = patient;
        this.doctor = doctor;
        this.medications = new ArrayList<>();
        this.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        this.sentToPharmacy = false;
    }

    public void addMedications(ArrayList<Medication> list) {
        for (int i = 0; i < list.size(); i++) {
            Medication m = list.get(i);
            Medication copy = new Medication(m.getName(), m.getDosage(), m.getInstructions());
            medications.add(copy);
        }
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

    public void markSentToPharmacy() {
        sentToPharmacy = true;
    }

    @Override
    public String toString() {
        return "Prescription " + id + " | doctor: " + doctor.getName() + " | meds: "
                + medications.size() + " | time " + createdAt;
    }
}
