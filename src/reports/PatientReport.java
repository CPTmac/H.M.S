package reports;

import java.util.List;

public class PatientReport extends Report {

    private final List<String> patients;

    public PatientReport(List<String> patients) {
        this.patients = patients;
    }

    @Override
    public String generate() {
        StringBuilder output = new StringBuilder();

        output.append("===== PATIENT REPORT =====\n");
        output.append(formatLine("Total Patients", patients.size()));
        output.append("----------------------------------------\n");

        for (int i = 0; i < patients.size(); i++) {
            output.append(formatLine("Patient " + (i + 1), patients.get(i)));
        }

        output.append("========================================\n");
        return output.toString();
    }
}

