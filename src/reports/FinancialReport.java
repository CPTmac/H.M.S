package reports;

public class FinancialReport extends Report {

    private final double money;

    public FinancialReport(double money) {
        this.money = money;
    }

    @Override
    public void generate() {
        // Print the title and the primary financial metric for this report.
        System.out.println("===== FINANCIAL REPORT =====");
        printLine("Total Money", String.format("$%.2f", money));
        System.out.println("================================");
    }
}
