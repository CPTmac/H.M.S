package services;

import exceptions.LabProblemException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import models.LabResult;
import models.LaboratoryCatalog;
import models.LaboratoryTest;
import models.LaboratoryTestStatus;

public class LaboratoryService {

    // ليستة التحاليل
    private final ArrayList<LaboratoryTest> tests;

    // Constructor
    public LaboratoryService() {

        tests = new ArrayList<>();
    }

    /*
     * =========================
     * Create Lab Test
     * =========================
     */

    public LaboratoryTest createLabTest(
            String patientId,
            String doctorId,
            String testName,
            double cost
    ) throws LabProblemException {

        // validation
        if (cost <= 0) {

            throw new LabProblemException(
                    "Cost must be greater than zero."
            );
        }

        // generate ID
        String id =
                UUID.randomUUID().toString();

        // create object
        LaboratoryTest test =
                new LaboratoryTest(
                        id,
                        patientId,
                        doctorId,
                        testName,
                        cost
                );

        // save
        tests.add(test);

                // If a catalog entry matches the requested test, normalize the stored test name
                try {
                        int idx;
                        if (testName.matches("\\d+")) {
                                idx = Integer.parseInt(testName) - 1;
                        } else {
                                idx = LaboratoryCatalog.resolveIndexByKeyword(testName);
                        }

                        String disp = LaboratoryCatalog.getDisplayNameByIndex(idx);
                        String abbr = LaboratoryCatalog.getAbbreviationByIndex(idx);
                        test.setTestName(abbr + " - " + disp);
                } catch (Exception ignored) {
                        // If no catalog match, keep provided testName as-is.
                }

                // If a billing already exists for the patient, add this test cost to it.
                try {
                        services.BillingService.getInstance().addTestCostToPatient(patientId, cost);
                } catch (Exception ignored) {
                        // If billing service is not available or update fails, do not block lab creation.
                }

        return test;
    }

    /*
     * =========================
     * Attach Result
     * =========================
     */

    public void attachResult(
            String testId,
            LabResult result
    ) throws LabProblemException {

        LaboratoryTest test =
                findTestById(testId);

        if (test == null) {

            throw new LabProblemException(
                    "Test not found."
            );
        }

        if (test.getStatus()
                == LaboratoryTestStatus.DONE) {

            throw new LabProblemException(
                    "Result already exists."
            );
        }

        if (result == null) {

            throw new LabProblemException(
                    "Result cannot be null."
            );
        }

        if (!test.getId()
                .equals(result.getTestId())) {

            throw new LabProblemException(
                    "Test ID mismatch."
            );
        }

        // save result
        test.setResult(result);

        test.setStatus(
                LaboratoryTestStatus.DONE
        );
    }

    /*
     * =========================
     * Find Test
     * =========================
     */

    public LaboratoryTest findTestById(
            String testId
    ) {

        for (LaboratoryTest test : tests) {

            if (test.getId()
                    .equalsIgnoreCase(testId)) {

                return test;
            }
        }

        return null;
    }

    /*
     * =========================
     * Get All Tests
     * =========================
     */

    public List<LaboratoryTest> getAllTests() {

        return new ArrayList<>(tests);
    }

    /*
     * =========================
     * Display Available Tests
     * =========================
     */

    public void displayAvailableTests() {

        System.out.println(
                LaboratoryCatalog.buildMenuText()
        );
    }

    /*
     * =========================
     * Find Price By Menu
     * =========================
     */

    public double getPriceFromMenu(
            int menuNumber
    ) throws LabProblemException {

        return LaboratoryCatalog
                .priceFromMenuRow(menuNumber);
    }

    /*
     * =========================
     * Find Price By Keyword
     * =========================
     */

    public double getPriceByKeyword(
            String keyword
    ) throws LabProblemException {

        return LaboratoryCatalog
                .lookupPriceByKeyword(keyword);
    }
}