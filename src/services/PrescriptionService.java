package services;

import models.Doctor;
import models.Medication;
import models.Patient;
import models.Prescription;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PrescriptionService {

    // ليستة الروشتات
    private final ArrayList<Prescription> prescriptions;

    public PrescriptionService() {

        prescriptions = new ArrayList<>();
    }

    /*
     * إنشاء روشتة جديدة
     */

    public Prescription createPrescription(
            Patient patient,
            Doctor doctor,
            ArrayList<Medication> medications
    ) {

        // توليد ID
        String id =
                UUID.randomUUID().toString();

        // إنشاء وقت الإنشاء
        String createdAt =
                new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm"
                ).format(new Date());

        // نسخ الأدوية
        ArrayList<Medication> copiedMedications =
                new ArrayList<>();

        for (Medication medication : medications) {

            Medication copy =
                    new Medication(
                            medication.getName(),
                            medication.getDosage(),
                            medication.getInstructions()
                    );

            copiedMedications.add(copy);
        }

        // إنشاء الروشتة
        Prescription prescription =
                new Prescription(
                        id,
                        patient,
                        doctor,
                        copiedMedications,
                        createdAt
                );

        // حفظ الروشتة
        prescriptions.add(prescription);

        System.out.println(
                "Prescription created successfully."
        );

        return prescription;
    }

    /*
     * إرسال الروشتة للصيدلية
     */

    public boolean sendToPharmacy(String prescriptionId) {

        for (Prescription prescription
                : prescriptions) {

            if (prescription.getId()
                    .equalsIgnoreCase(prescriptionId)) {

                prescription.setSentToPharmacy(true);

                System.out.println(
                        "Prescription sent successfully."
                );

                return true;
            }
        }

        System.out.println(
                "Prescription not found."
        );

        return false;
    }

    /*
     * البحث عن روشتة
     */

    public Prescription findPrescriptionById(
            String prescriptionId
    ) {

        for (Prescription prescription
                : prescriptions) {

            if (prescription.getId()
                    .equalsIgnoreCase(prescriptionId)) {

                return prescription;
            }
        }

        return null;
    }

    /*
     * إرجاع كل الروشتات
     */

    public List<Prescription> getAllPrescriptions() {

        return new ArrayList<>(prescriptions);
    }

    /*
     * حذف روشتة
     */

    public boolean removePrescription(
            String prescriptionId
    ) {

        for (Prescription prescription
                : prescriptions) {

            if (prescription.getId()
                    .equalsIgnoreCase(prescriptionId)) {

                prescriptions.remove(prescription);

                System.out.println(
                        "Prescription removed successfully."
                );

                return true;
            }
        }

        return false;
    }
}