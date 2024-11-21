package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class application {

    // Use one Scanner object throughout the class
    private static final Scanner sc = new Scanner(System.in);

    public void addApplication() {
        config conf = new config();
        applicant apl = new applicant();

        // Display list of applicants
        apl.viewapplicant();

        // Get and validate applicant ID
        System.out.println("Enter the applicant ID you want to apply with: ");
        String apl_id = sc.nextLine();
        String sql = "SELECT apl_id FROM applicant_information WHERE apl_id = ?";

        while (conf.getSingleValue(sql, apl_id) == 0) {
            System.out.println("Applicant does not exist, please select again: ");
            apl_id = sc.nextLine();
        }

        jobs jb = new jobs();
        jb.viewjobs();

        // Get and validate job ID
        System.out.println("Enter the job ID you want to apply for: ");
        String job_id = sc.nextLine();

        String jsql = "SELECT job_id FROM jobs WHERE job_id = ?";

        while (conf.getSingleValue(jsql, job_id) == 0) {
            System.out.println("The job ID does not exist, please select again: ");
            job_id = sc.nextLine();
        }

        // Insert application record
        String apl_status = "pending";
        String appqry = "INSERT INTO application (apl_id, job_id, app_status) VALUES (?, ?, ?)";

        // Ensure the insert is only executed once all inputs are valid
        conf.addRecord(appqry, apl_id, job_id, apl_status);
        System.out.println("Application successfully submitted!");
    }

    public void viewApplication() {
        String appQuery = "SELECT application.apl_id, application.app_id, applicant_information.apl_fname, applicant_information.apl_lname, "
                + "jobs.job_name, jobs.job_salary, application.app_status "
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

        // SQL query to check if the applicant exists based on applicant_id
        String checkSql = "SELECT apl_id FROM applicant_information WHERE apl_id = ?";

        // Prompt user for Applicant ID
        System.out.print("Enter Applicant ID: ");
        int applicantId = 0;

        // Validate Applicant ID input
        while (true) {
            try {
                applicantId = Integer.parseInt(sc.nextLine());
                break; // Valid input, break the loop
            } catch (NumberFormatException e) {
                System.out.println("Invalid Applicant ID. Please enter a valid number.");
            }
        }

        // Check if the Applicant ID exists
        while (conf.getSingleValue(checkSql, applicantId) == 0) {
            System.out.println("Invalid Applicant ID. Please try again.");
            System.out.print("Enter Applicant ID: ");
            applicantId = Integer.parseInt(sc.nextLine());
        }

        // Prompt for the new application status
        System.out.print("Enter new Application Status: ");
        String status = sc.nextLine();

        // SQL query to update the application status using the applicant_id
        String updateSql = "UPDATE application SET app_status = ? WHERE apl_id = ?";

        // Update the status for the given applicant_id
        try {
            conf.updateRecord(updateSql, status, applicantId);
            System.out.println("Application status updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating application status: " + e.getMessage());
        }
    }

    private void deleteapplication() {
        System.out.print("Enter the Application ID to Delete: ");
        int id = Integer.parseInt(sc.nextLine()); // Directly read as integer

        String qry = "DELETE FROM application WHERE app_id = ?";

        config conf = new config();
        conf.deleteRecord(qry, id);
    }

    public void Application() {
        boolean exit = true;

        do {
            try {
                System.out.println("1. ADD APPLICATION");
                System.out.println("2. VIEW APPLICATION");
                System.out.println("3. APPROVAL AND UPDATE");
                System.out.println("4. DELETE APPLICATION");
                System.out.println("5. EXIT");

                System.out.print("Enter Action: ");

                // Check if the next input is an integer
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next(); // Clear the invalid input
                    continue; // Skip to the next iteration of the loop
                }

                int action = sc.nextInt();  // Read user action input
                sc.nextLine(); // Clear the buffer

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
                        String resp = sc.nextLine();  // Read the 'yes' response
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid action, please enter a choice between 1-5!!!!\n");
                        break;
                }

            } catch (InputMismatchException e) {
                System.err.println("Error: Invalid input. Please enter a valid number.");
                sc.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                sc.nextLine(); // Consume the invalid input
            }

        } while (exit);
    }
}
