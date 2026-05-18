package reports;

public class FinancialReport extends Report {

    private double money;

    public FinancialReport(double money) {
        this.money = money;
    }

    @Override
    public void generate() {

        System.out.println("===== FINANCIAL REPORT =====");
        System.out.println("Total Money: " + money);
    }
}
