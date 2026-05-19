package reports;

import java.util.List;

public class PatientReport extends Report {

    private final List<String> patients;

    public PatientReport(List<String> patients) {
        this.patients = patients;
    }

    @Override
    public void generate() {
        // Print the report title and a simple patient summary.
        System.out.println("===== PATIENT REPORT =====");
        printLine("Total Patients", patients.size());
        System.out.println("----------------------------------------");

        // Print each patient entry in the provided list.
        for (int i = 0; i < patients.size(); i++) {
            printLine("Patient " + (i + 1), patients.get(i));
        }

        System.out.println("========================================");
    }
}

