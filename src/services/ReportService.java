package services;

import reports.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    // ليستة التقارير
    private final ArrayList<Report> reports;

    // Constructor
    public ReportService() {

        reports = new ArrayList<>();
    }

    /*
     * =========================
     * Add Report
     * =========================
     */

    public void addReport(Report report) {

        if (report == null) {

            System.out.println(
                    "Report cannot be null."
            );

            return;
        }

        reports.add(report);

        System.out.println(
                "Report added successfully."
        );
    }

    /*
     * =========================
     * Generate Single Report
     * =========================
     */

    public void generateReport(
            Report report
    ) {

        if (report == null) {

            System.out.println(
                    "Report not found."
            );

            return;
        }

        report.generate();
    }

    /*
     * =========================
     * Generate All Reports
     * =========================
     */

    public void generateAllReports() {

        if (reports.isEmpty()) {

            System.out.println(
                    "No reports available."
            );

            return;
        }

        for (Report report : reports) {

            report.generate();

            System.out.println(
                    "-------------------"
            );
        }
    }

    /*
     * =========================
     * Remove Report
     * =========================
     */

    public boolean removeReport(
            Report report
    ) {

        if (reports.remove(report)) {

            System.out.println(
                    "Report removed successfully."
            );

            return true;
        }

        System.out.println(
                "Report not found."
        );

        return false;
    }

    /*
     * =========================
     * Get All Reports
     * =========================
     */

    public List<Report> getAllReports() {

        return new ArrayList<>(reports);
    }

    /*
     * =========================
     * Display Reports
     * =========================
     */

    public void displayReports() {

        if (reports.isEmpty()) {

            System.out.println(
                    "No reports available."
            );

            return;
        }

        for (Report report : reports) {

            System.out.println(
                    report.getClass().getSimpleName()
            );
        }
    }
}