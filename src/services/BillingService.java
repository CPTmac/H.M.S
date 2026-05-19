package services;

import java.util.ArrayList;
import java.util.List;
import models.Billing;
import models.Patient;

public class BillingService {

    private static BillingService instance;

    private final ArrayList<Billing> bills;

    private BillingService() {
        bills = new ArrayList<>();
    }

    // Simple singleton accessor so other services can update bills centrally.
    public static BillingService getInstance() {
        if (instance == null) {
            instance = new BillingService();
        }
        return instance;
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
        billing.setPaymentMethod("Unpaid");

        billing.calculateBill();

        bills.add(billing);

        System.out.println("Bill created successfully.");
    }

    // =========================
    // PAY BILL (FIXED ID SYSTEM)
    // =========================
    public boolean payBill(String patientId, String paymentMethod) {

        for (Billing billing : bills) {

            Patient p = billing.getPatient();

            if (p != null && p.getId().equalsIgnoreCase(patientId)) {

                billing.setPaymentMethod(paymentMethod);
                billing.payBill();
                billing.calculateBill();

                System.out.println("Payment completed with " + paymentMethod + ".");
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

    public double getTotalDueByPatientId(String patientId) {
        Billing billing = findBillByPatientId(patientId);
        return billing == null ? 0.0 : billing.getTotalDue();
    }

    // Add test cost to an existing bill for a patient. If no bill exists, nothing happens.
    public boolean addTestCostToPatient(String patientId, double cost) {
        Billing b = findBillByPatientId(patientId);
        if (b == null) return false;
        b.setTestCost(b.getTestCost() + cost);
        b.markUnpaid();
        b.calculateBill();
        System.out.println("Added test cost " + cost + " to patient " + patientId);
        return true;
    }

    // Add test cost and create a bill if one does not already exist.
    public boolean addTestCostToPatient(Patient patient, double cost) {
        if (patient == null) return false;
        Billing billing = findBillByPatientId(patient.getId());
        if (billing == null) {
            createBill(patient, 0.0, 0.0, cost);
            return true;
        }
        billing.setTestCost(billing.getTestCost() + cost);
        billing.markUnpaid();
        billing.calculateBill();
        System.out.println("Added test cost " + cost + " to patient " + patient.getId());
        return true;
    }

    // Add room cost to existing bill or create one when booking.
    public void addOrCreateRoomCost(Patient patient, double roomCost) {
        if (patient == null) return;
        Billing existing = findBillByPatientId(patient.getId());
        if (existing != null) {
            existing.setRoomCost(existing.getRoomCost() + roomCost);
            existing.markUnpaid();
            existing.calculateBill();
            System.out.println("Updated room cost for " + patient.getId());
        } else {
            createBill(patient, roomCost, 0.0, 0.0);
        }
    }

    // Add medicine cost to an existing bill for a patient.
    public boolean addMedicineCostToPatient(String patientId, double cost) {
        Billing b = findBillByPatientId(patientId);
        if (b == null) {
            return false;
        }
        b.setMedicineCost(b.getMedicineCost() + cost);
        b.markUnpaid();
        b.calculateBill();
        System.out.println("Added medicine cost " + cost + " to patient " + patientId);
        return true;
    }

    // Add medicine cost and create a bill if one does not already exist.
    public boolean addMedicineCostToPatient(Patient patient, double cost) {
        if (patient == null) return false;
        Billing billing = findBillByPatientId(patient.getId());
        if (billing == null) {
            createBill(patient, 0.0, cost, 0.0);
            return true;
        }
        billing.setMedicineCost(billing.getMedicineCost() + cost);
        billing.markUnpaid();
        billing.calculateBill();
        System.out.println("Added medicine cost " + cost + " to patient " + patient.getId());
        return true;
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