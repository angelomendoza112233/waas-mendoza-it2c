package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class jobs {

    private final Scanner sc = new Scanner(System.in); 
    private final config conf = new config();          
    public void add_jobs() {
        try {
            System.out.println("Enter Job ID (enter the given job ID): ");
            String job_id = validateNumericInput("Job ID");

            System.out.println("Enter Job Name: ");
            String job_name = sc.nextLine().trim();
            while (job_name.isEmpty()) {
                System.out.println("Job name cannot be empty. Please enter a valid name: ");
                job_name = sc.nextLine().trim();
            }

            System.out.println("Enter Job Salary (numeric): ");
            String job_salary = validateNumericInput("Job Salary");

            String sql = "INSERT INTO jobs(job_id, job_name, job_salary) VALUES (?, ?, ?)";
            conf.addRecord(sql, job_id, job_name, job_salary);

            System.out.println("Job added successfully!");

        } catch (Exception e) {
            System.err.println("Error adding job: " + e.getMessage());
        }
    }

    public void viewjobs() {
        try {
            String jobQuery = "SELECT * FROM jobs";
            System.out.println("JOB PANEL");
            String[] jobHeaders = {"Job ID", "Job Name", "Job Salary"};
            String[] jobColumns = {"job_id", "job_name", "job_salary"};
            conf.viewRecords(jobQuery, jobHeaders, jobColumns);
        } catch (Exception e) {
            System.err.println("Error retrieving jobs: " + e.getMessage());
        }
    }

    private void updatejobs() {
        try {
            System.out.println("Enter Job ID to Update (numeric): ");
            String job_id = validateNumericInput("Job ID");

            System.out.println("Enter the new salary (numeric): ");
            String new_salary = validateNumericInput("New Salary");

            String qry = "UPDATE jobs SET job_salary = ? WHERE job_id = ?";
            conf.updateRecord(qry, new_salary, job_id);

            System.out.println("Job updated successfully!");

        } catch (Exception e) {
            System.err.println("Error updating job: " + e.getMessage());
        }
    }

    private void deletejobs() {
        try {
            System.out.println("Enter Job ID to Delete (numeric): ");
            String job_id = validateNumericInput("Job ID");

            String qry = "DELETE FROM jobs WHERE job_id = ?";
            conf.deleteRecord(qry, job_id);

            System.out.println("Job deleted successfully!");

        } catch (Exception e) {
            System.err.println("Error deleting job: " + e.getMessage());
        }
    }

    public void job() {
        boolean exit = true;

        do {
            try {
                System.out.println("|--------------------------------------|");
                System.out.println("1. ADD JOBS");
                System.out.println("2. VIEW JOBS");
                System.out.println("3. UPDATE JOBS");
                System.out.println("4. DELETE JOBS");
                System.out.println("5. EXIT");

                System.out.print("Enter Action: ");
                int action = Integer.parseInt(sc.nextLine()); 

                switch (action) {
                    case 1:
                        add_jobs();
                        break;
                    case 2:
                        viewjobs();
                        break;
                    case 3:
                        viewjobs();
                        updatejobs();
                        break;
                    case 4:
                        viewjobs();
                        deletejobs();
                        break;
                    case 5:
                        System.out.println("Exit Selected...type 'yes' to move to the main panel");
                        String resp = sc.nextLine();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid action. Please select a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (exit);
    }

    /**
     * Utility method to validate numeric input from the user.
     * 
     * @param fieldName The name of the field being validated.
     * @return A valid numeric string input.
     */
    private String validateNumericInput(String fieldName) {
        String input;
        while (true) {
            input = sc.nextLine().trim();
            if (input.matches("\\d+")) {
                return input;
            } else {
                System.out.println("Invalid " + fieldName + ". Please enter a numeric value: ");
            }
        }
    }
}
