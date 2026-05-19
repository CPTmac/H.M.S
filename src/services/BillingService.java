package services;

import java.util.ArrayList;
import java.util.List;
import models.Billing;
import models.Patient;

public class BillingService {

    private final ArrayList<Billing> bills;

    public BillingService() {
        bills = new ArrayList<>();
    }

    // =========================
    // CREATE BILL
    // =========================
    public void createBill(Patient patient,
                           double roomCost,
                           double medicineCost,
                           double testCost) {

        if (patient == null) {
            System.out.println("Patient cannot be null.");
            return;
        }

        Billing billing = new Billing(patient);

        billing.setRoomCost(roomCost);
        billing.setMedicineCost(medicineCost);
        billing.setTestCost(testCost);

        billing.calculateBill();

        bills.add(billing);

        System.out.println("Bill created successfully.");
    }

    // =========================
    // PAY BILL (FIXED ID SYSTEM)
    // =========================
    public boolean payBill(String patientId) {

        for (Billing billing : bills) {

            Patient p = billing.getPatient();

            if (p != null && p.getId().equalsIgnoreCase(patientId)) {

                billing.payBill();

                System.out.println("Payment completed.");
                return true;
            }
        }

        System.out.println("Bill not found.");
        return false;
    }

    // =========================
    // FIND BILL
    // =========================
    public Billing findBillByPatientId(String patientId) {

        for (Billing billing : bills) {

            Patient p = billing.getPatient();

            if (p != null && p.getId().equalsIgnoreCase(patientId)) {
                return billing;
            }
        }

        return null;
    }

    // =========================
    // GET ALL
    // =========================
    public List<Billing> getAllBills() {
        return new ArrayList<>(bills);
    }

    // =========================
    // DISPLAY
    // =========================
    public void displayBills() {

        for (Billing billing : bills) {
            System.out.println(billing);
            System.out.println("-------------------");
        }
    }

    // =========================
    // REMOVE SAFE
    // =========================
    public boolean removeBill(String patientId) {

        Billing target = null;

        for (Billing billing : bills) {

            Patient p = billing.getPatient();

            if (p != null && p.getId().equalsIgnoreCase(patientId)) {
                target = billing;
                break;
            }
        }

        if (target != null) {

            bills.remove(target);

            System.out.println("Bill removed successfully.");
            return true;
        }

        System.out.println("Bill not found.");
        return false;
    }
}