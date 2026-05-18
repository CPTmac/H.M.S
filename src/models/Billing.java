public class Billing {

    private Patient patient;

    private double roomCost;
    private double medicineCost;
    private double testCost;

    private double totalAmount;

    private boolean paid;

    public Billing(Patient patient) {

        this.patient = patient;
        paid = false;
    }

    // calculate bill
    public void calculateBill() {

        totalAmount =
                roomCost +
                medicineCost +
                testCost;
    }

    public void payBill() {
        paid = true;
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

    // getters

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public String toString() {
        return "Billing for Patient: " + patient.getName() + "\n" + "Room Cost: " + roomCost + "\n" + "Medicine Cost: " + medicineCost + "\n" + "Test Cost: " + testCost + "\n" + "Total Amount: " + totalAmount + "\n" + "Paid: " + (paid ? "Yes" : "No");
    }
}