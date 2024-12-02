package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class application {

    private static final Scanner sc = new Scanner(System.in);

    public void addApplication() {
        config conf = new config();
        applicant apl = new applicant();

        // Display list of applicants
        apl.viewapplicant();

        // Validate Applicant ID
        String apl_id;
        while (true) {
            System.out.println("Enter the applicant ID you want to apply with: ");
            apl_id = sc.nextLine();
            if (conf.getSingleValue("SELECT apl_id FROM applicant_information WHERE apl_id = ?", apl_id) != 0) {
                break; // Valid ID found
            } else {
                System.out.println("Invalid Applicant ID. Please try again.");
            }
        }

        jobs jb = new jobs();
        jb.viewjobs();

        // Validate Job ID
        String job_id;
        while (true) {
            System.out.println("Enter the job ID you want to apply for: ");
            job_id = sc.nextLine();
            if (conf.getSingleValue("SELECT job_id FROM jobs WHERE job_id = ?", job_id) != 0) {
                break; // Valid Job ID found
            } else {
                System.out.println("Invalid Job ID. Please try again.");
            }
        }

        // Insert Application Record
        String apl_status = "pending";
        String appqry = "INSERT INTO application (apl_id, job_id, app_status) VALUES (?, ?, ?)";
        conf.addRecord(appqry, apl_id, job_id, apl_status);
        System.out.println("Application successfully submitted!");
    }

    public void viewApplication() {
        String appQuery = "SELECT application.apl_id, application.app_id, applicant_information.apl_fname, "
                + "applicant_information.apl_lname, jobs.job_name, jobs.job_salary, application.app_status "
                + "FROM application "
                + "LEFT JOIN applicant_information ON applicant_information.apl_id = application.apl_id "
                + "LEFT JOIN jobs ON jobs.job_id = application.job_id";

        String[] appHeaders = {"Application ID", "Applicant ID", "First Name", "Last Name", "Job Name", "Job Salary", "Status"};
        String[] appColumns = {"app_id", "apl_id", "apl_fname", "apl_lname", "job_name", "job_salary", "app_status"};
        config conf = new config();

        try {
            conf.viewRecords(appQuery, appHeaders, appColumns);
        } catch (Exception e) {
            System.err.println("Error retrieving records: " + e.getMessage());
        }
    }

    private void updatestatus() {
        config conf = new config();

        // Validate Applicant ID
        int applicantId;
        while (true) {
            System.out.print("Enter Applicant ID: ");
            try {
                applicantId = Integer.parseInt(sc.nextLine());
                if (conf.getSingleValue("SELECT apl_id FROM applicant_information WHERE apl_id = ?", applicantId) != 0) {
                    break; // Valid ID found
                } else {
                    System.out.println("Invalid Applicant ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric Applicant ID.");
            }
        }

        // Validate Status
        String status;
        while (true) {
            System.out.print("Enter new Application Status (pending, approved, rejected): ");
            status = sc.nextLine().toLowerCase();
            if (status.equals("pending") || status.equals("approved") || status.equals("rejected")) {
                break; // Valid status
            } else {
                System.out.println("Invalid status. Please enter one of the following: pending, approved, rejected.");
            }
        }

        // Update Status
        try {
            String updateSql = "UPDATE application SET app_status = ? WHERE apl_id = ?";
            conf.updateRecord(updateSql, status, applicantId);
            System.out.println("Application status updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating application status: " + e.getMessage());
        }
    }

    private void deleteapplication() {
        config conf = new config();
        int appId;

        // Validate Application ID
        while (true) {
            System.out.print("Enter the Application ID to Delete: ");
            try {
                appId = Integer.parseInt(sc.nextLine());
                if (conf.getSingleValue("SELECT app_id FROM application WHERE app_id = ?", appId) != 0) {
                    break; // Valid Application ID
                } else {
                    System.out.println("Invalid Application ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric Application ID.");
            }
        }

        // Delete Application
        try {
            String deleteSql = "DELETE FROM application WHERE app_id = ?";
            conf.deleteRecord(deleteSql, appId);
            System.out.println("Application deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting application: " + e.getMessage());
        }
    }

    public void Application() {
        boolean exit = true;

        do {
            try {
                System.out.println("|--------------------------------------|");
                System.out.println("1. ADD APPLICATION");
                System.out.println("2. VIEW APPLICATION");
                System.out.println("3. APPROVAL AND UPDATE");
                System.out.println("4. DELETE APPLICATION");
                System.out.println("5. EXIT");

                System.out.print("Enter Action: ");
                int action = Integer.parseInt(sc.nextLine()); // Parse action safely

                switch (action) {
                    case 1:
                        addApplication();
                        break;
                    case 2:
                        viewApplication();
                        break;
                    case 3:
                        viewApplication();
                        updatestatus();
                        break;
                    case 4:
                        viewApplication();
                        deleteapplication();
                        break;
                    case 5:
                        System.out.println("Exit Selected...type 'yes' to continue to move to the main panel");
                        String resp = sc.nextLine();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid action. Please enter a choice between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (exit);
    }
}
