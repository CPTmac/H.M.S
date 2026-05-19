package reports;

import models.Billing;

public class FinancialReport extends Report {

    private final Billing billing;

    public FinancialReport(Billing billing) {
        this.billing = billing;
    }

    @Override
    public String generate() {
        StringBuilder output = new StringBuilder();

        output.append("===== FINANCIAL REPORT =====\n");
        output.append(formatLine("Patient", billing.getPatient().getName()));
        output.append(formatLine("Room Cost", String.format("EGP %.2f", billing.getRoomCost())));
        output.append(formatLine("Test Cost", String.format("EGP %.2f", billing.getTestCost())));
        output.append(formatLine("Medicine Cost", String.format("EGP %.2f", billing.getMedicineCost())));
        output.append(formatLine("Total Amount", String.format("EGP %.2f", billing.getTotalAmount())));
        output.append(formatLine("Total Due", String.format("EGP %.2f", billing.getTotalDue())));
        output.append(formatLine("Payment Method", billing.getPaymentMethod()));
        output.append(formatLine("Payment Status", billing.isPaid() ? "Paid" : "Unpaid"));
        output.append("================================\n");

        return output.toString();
    }
}
