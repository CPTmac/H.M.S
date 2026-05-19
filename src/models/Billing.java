package models;

public class Billing {

    @SuppressWarnings("FieldMayBeFinal")
    private Patient patient;

    private double roomCost;
    private double medicineCost;
    private double testCost;

    private double totalAmount;
    private double totalDue;

    private boolean paid;

    public Billing(Patient patient) {

        this.patient = patient;
        paid = false;
        totalAmount = 0.0;
        totalDue = 0.0;
    }

    // calculate bill totals and due balance
    public void calculateBill() {

        totalAmount = roomCost + medicineCost + testCost;
        totalDue = paid ? 0.0 : totalAmount;
    }

    public void payBill() {
        paid = true;
        totalDue = 0.0;
    }

    public void markUnpaid() {
        paid = false;
        totalDue = totalAmount;
    }

    // setters

    public void setRoomCost(double roomCost) {
        this.roomCost = roomCost;
    }

    public void setMedicineCost(double medicineCost) {
        this.medicineCost = medicineCost;
    }

    public void setTestCost(double testCost) {
        this.testCost = testCost;
    }

    // getters for individual cost components (used when updating bills)
    public double getRoomCost() {
        return roomCost;
    }

    public double getMedicineCost() {
        return medicineCost;
    }

    public double getTestCost() {
        return testCost;
    }

    // getters

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public boolean isPaid() {
        return paid;
    }

    @SuppressWarnings("override")
    public String toString() {
        return "Billing for Patient: " + patient.getName() + "\n" +
                "Room Cost: " + roomCost + "\n" +
                "Medicine Cost: " + medicineCost + "\n" +
                "Test Cost: " + testCost + "\n" +
                "Total Amount: " + totalAmount + "\n" +
                "Total Due: " + totalDue + "\n" +
                "Paid: " + (paid ? "Yes" : "No");
    }

    public Patient getPatient() {
        return patient;
    }
}