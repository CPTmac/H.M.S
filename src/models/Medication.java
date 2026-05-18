package models;

import java.io.Serializable;

/** One line on a prescription */
public class Medication implements Serializable {

    private String name;
    private String dosage;
    private String instructions;

    public Medication(String name, String dosage, String instructions) {
        this.name = name;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
