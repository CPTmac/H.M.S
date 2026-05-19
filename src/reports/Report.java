package reports;

public abstract class Report {
    /**
     * Generate the report output in a consistent format.
     * Concrete report implementations must return the formatted text.
     */
    public abstract String generate();

    /**
     * Format a single report line with an aligned label and value.
     */
    protected String formatLine(String label, Object value) {
        return String.format("%-23s: %s%n", label, value);
    }
}

