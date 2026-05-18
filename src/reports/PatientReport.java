package reports;

import java.util.List;

public class PatientReport extends Report {

    private List<String> patients;

    public PatientReport(List<String> patients) {
        this.patients = patients;
    }

    @Override
    public void generate() {

        System.out.println("===== PATIENT REPORT =====");
        System.out.println("Total Patients: " + patients.size());

        for(String p : patients) {
            System.out.println(p);
        }
    }
}
