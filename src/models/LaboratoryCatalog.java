package models;

import exceptions.LabProblemException;
import java.util.ArrayList;

/**
 * قائمة التحاليل المتاحة وأسعارها (متطلب المشروع).
 * الأسعار تجريبية للمشروع فقط وليست أسعار جهة حقيقية.
 */
public final class LaboratoryCatalog {

    /** عمود الاسم المعروض | عمود السعر كنص */
    public static final String[][] AVAILABLE_TESTS_AND_PRICES =
            new String[][] {
                {"CBC - Complete Blood Count", "145.50"},
                {"Lipid Panel", "215.75"},
                {"HbA1c", "180.00"},
                {"TSH", "165.40"},
                {"Liver Function Panel", "275.90"},
                {"Blood Glucose - Fasting", "75.25"},
                {"Urine Routine Analysis", "90.00"},
            };

    private LaboratoryCatalog() {}

    public static String buildMenuText() {
        StringBuilder b = new StringBuilder();
        b.append("--- Available lab tests (name | price) ---\n");
        for (int i = 0; i < AVAILABLE_TESTS_AND_PRICES.length; i++) {
            b.append(i + 1)
                    .append(". ")
                    .append(AVAILABLE_TESTS_AND_PRICES[i][0])
                    .append("  |  ")
                    .append(AVAILABLE_TESTS_AND_PRICES[i][1])
                    .append(" EGP\n");
        }
        return b.toString();
    }

    public static double priceFromMenuRow(int menuNumberOneBased) throws LabProblemException {
        if (menuNumberOneBased < 1 || menuNumberOneBased > AVAILABLE_TESTS_AND_PRICES.length) {
            throw new LabProblemException("bad menu choice for lab");
        }
        return Double.parseDouble(AVAILABLE_TESTS_AND_PRICES[menuNumberOneBased - 1][1]);
    }

    public static double lookupPriceByKeyword(String keyword) throws LabProblemException {
        if (keyword == null || keyword.trim().equals("")) {
            throw new LabProblemException("lab keyword is empty");
        }
        String needle = keyword.trim().toLowerCase();
        ArrayList<Integer> hits = collectMatchIndexes(needle);
        if (hits.isEmpty()) {
            throw new LabProblemException("lab name \"" + keyword + "\" not in catalog - see buildMenuText()");
        }
        if (hits.size() > 1) {
            throw new LabProblemException("more than one test matches \"" + keyword
                    + "\" - use priceFromMenuRow(number) instead");
        }
        int idx = hits.get(0);
        return Double.parseDouble(AVAILABLE_TESTS_AND_PRICES[idx][1]);
    }

    private static ArrayList<Integer> collectMatchIndexes(String needle) {
        ArrayList<Integer> hits = new ArrayList<>();
        for (int i = 0; i < AVAILABLE_TESTS_AND_PRICES.length; i++) {
            String nameLower = AVAILABLE_TESTS_AND_PRICES[i][0].toLowerCase();
            if (nameLower.contains(needle)) {
                hits.add(i);
            }
        }
        return hits;
    }
}
