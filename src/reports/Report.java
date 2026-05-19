package reports;

public abstract class Report {
    /**
     * Generate the report output in a consistent format.
     * Concrete report implementations must provide their own report data.
     */
    public abstract void generate();

    /**
     * Print a single report line with an aligned label and value.
     * This helper keeps the formatting consistent across reports.
     */
    protected void printLine(String label, Object value) {
        System.out.println(String.format("%-23s: %s", label, value));
    }
}

