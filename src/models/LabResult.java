package models;

import java.io.Serializable;

/** Result attached to LaboratoryTest when ready */
public class LabResult implements Serializable {

    private String testId;
    private String resultText;
    private String notes;
    private String date;

    public LabResult(String testId, String resultText, String notes, String date) {
        this.testId = testId;
        this.resultText = resultText;
        this.notes = notes == null ? "" : notes;
        this.date = date;
    }

    public String getTestId() {
        return testId;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LabResult order=" + testId + " -> " + resultText + " (date=" + date + ")";
    }
}
