package reports;

import java.util.List;
import models.Appointment;
import models.MedicalRecord;
import models.Patient;

public class MedicalReport extends Report {

    private final Patient patient;
    private final List<Appointment> appointments;

    public MedicalReport(Patient patient,
                         List<Appointment> appointments) {

        this.patient = patient;
        this.appointments = appointments;
    }

    @Override
    public String generate() {
        StringBuilder output = new StringBuilder();

        output.append("===== MEDICAL REPORT =====\n");
        output.append(formatLine("Patient ID", patient.getId()));
        output.append(formatLine("Name", patient.getName()));
        output.append(formatLine("Age", patient.getAge()));
        output.append(formatLine("Gender", patient.getGender()));

        MedicalRecord record = patient.getMedicalRecord();
        if (record != null) {
            output.append(formatLine("Blood Type", record.getBloodType()));
            output.append(formatLine("Diagnosis", record.getDiagnosis()));
            output.append(formatLine("Allergies", record.getAllergies()));
        }

        output.append("----------------------------------------\n");
        output.append("Appointment History:\n");

        if (appointments == null || appointments.isEmpty()) {
            output.append("No medical appointments recorded for this patient.\n");
        } else {
            for (Appointment appointment : appointments) {
                output.append(formatLine("Appointment ID", appointment.getAppointmentId()));
                output.append(formatLine("Doctor", appointment.getDoctor().getName()));
                output.append(formatLine("Date", appointment.getDate()));
                output.append(formatLine("Time", appointment.getTime()));
                output.append(formatLine("Room", appointment.getRoom()));
                output.append(formatLine("Diagnosis", appointment.getDiagnosis()));
                output.append(formatLine("Lab Tests", appointment.getLabTests()));
                output.append("----------------------------------------\n");
            }
        }

        output.append("========================================\n");
        return output.toString();
    }
}
