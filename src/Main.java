package Hospital-Management-System;
import java.util.Scanner;




public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalManagementSystem hms = new HospitalManagementSystem();

        while (true) {
            System.out.println("Welcome to the Hospital Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter patient age: ");
                    int patientAge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    hms.addPatient(patientName, patientAge);
                    break;
                case 2:
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter doctor specialization: ");
                    String doctorSpecialization = scanner.nextLine();
                    hms.addDoctor(doctorName, doctorSpecialization);
                    break;
                case 3:
                    System.out.print("Enter patient ID: ");
                    int patientId = scanner.nextInt();
                    System.out.print("Enter doctor ID: ");
                    int doctorId = scanner.nextInt();
                    hms.scheduleAppointment(patientId, doctorId);
                    break;
                case 4:
                    hms.viewAppointments();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
    }
}