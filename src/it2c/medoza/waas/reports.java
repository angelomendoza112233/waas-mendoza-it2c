package it2c.medoza.waas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

public class reports {

    public void reportsMenu() {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("|--------------------------------------|");
            System.out.println("\n========== REPORTS MENU ==========");
            System.out.println("1. General Report");
            System.out.println("2. Individual Report");
            System.out.println("3. Exit");

            int action;
            while (true) {
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                    if (action >= 1 && action <= 3) {
                        break;
                    }
                } else {
                    sc.next();
                }
                System.out.println("Invalid selection. Please enter a number between 1 and 3 only.");
            }

            switch (action) {
                case 1:
                    generalReport();
                    break;
                case 2:
                    individualReport(sc);
                    break;
                case 3:
                    System.out.println("Exiting the reports menu.");
                    return; // Exit the menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            // Ask if the user wants to continue
            while (true) {
                System.out.print("\nDo you want to continue? (yes/no): ");
                response = sc.next();
                if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'");
                }
            }

        } while (response.equalsIgnoreCase("yes"));

        System.out.println("Going back to the main menu...\n");
    }

    private void generalReport() {
        config conf = new config();
        System.out.println("\n--- General Report ---");

        // SQL query to fetch data using INNER JOIN between 3 tables
        String qry = "SELECT ai.apl_id, ai.apl_fname, ai.apl_lname, j.job_name, j.job_salary, ap.app_status "
                + "FROM applicant_information ai "
                + "INNER JOIN application ap ON ai.apl_id = ap.apl_id "
                + // Join applicant_information with application on apl_id
                "INNER JOIN jobs j ON ap.job_id = j.job_id"; // Join application with jobs on job_id

        // Column headers for the report
        String[] headers = {"Applicant ID", "First Name", "Last Name", "Job Name", "Job Salary", "Application Status"};
        String[] columns = {"apl_id", "apl_fname", "apl_lname", "job_name", "job_salary", "app_status"};

        // Call the method to display the records
        conf.viewRecords(qry, headers, columns);
    }

    private int validApplicantID(Scanner sc, config conf) {
        int applicantId;
        String sql = "SELECT COUNT(*) FROM applicant_information WHERE apl_id = ?";
        while (true) {
            System.out.print("Enter the Applicant ID: ");
            if (sc.hasNextInt()) {
                applicantId = sc.nextInt();
                if (conf.getSingleValue(sql, applicantId) > 0) {
                    return applicantId;
                } else {
                    System.out.println("Invalid Applicant ID. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Enter a numeric value.");
                sc.next();
            }
        }
    }

    private void individualReport(Scanner sc) {
        config conf = new config();
        System.out.println("\n--- Individual Applicant Report ---");

        // Display all applicants to help the user choose an ID
        String referenceQuery = "SELECT apl_id AS 'Applicant ID', apl_fname AS 'First Name' FROM applicant_information";

        try (Connection conn = conf.connectDB();
                PreparedStatement pstmt = conn.prepareStatement(referenceQuery);
                ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- Available Applicants ---");
            System.out.printf("%-15s %-20s\n", "Applicant ID", "First Name");
            System.out.println("-------------------------------");

            while (rs.next()) {
                System.out.printf("%-15d %-20s\n", rs.getInt("Applicant ID"), rs.getString("First Name"));
            }

            System.out.println("-------------------------------");

        } catch (SQLException e) {
            System.err.println("Error retrieving applicant list: " + e.getMessage());
            return; // Exit if the list cannot be retrieved
        }

        // Prompt the user to enter an Applicant ID
        int applicantId = validApplicantID(sc, conf);
        if (applicantId == -1) {
            System.out.println("Operation canceled. Returning to the reports menu.");
            return; // Exit if the user cancels
        }

        // SQL query to fetch the complete details
        String query = "SELECT ai.apl_id AS 'Applicant ID', ai.apl_fname AS 'First Name', ai.apl_lname AS 'Last Name', "
                + "ai.apl_address AS 'Address', j.job_name AS 'Job', ai.apl_sex AS 'Sex', ap.app_status AS 'Status' "
                + "FROM applicant_information ai "
                + "INNER JOIN application ap ON ai.apl_id = ap.apl_id "
                + "INNER JOIN jobs j ON ap.job_id = j.job_id "
                + "WHERE ai.apl_id = ?";

        // Execute the query and display the result
        try (Connection conn = conf.connectDB();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, applicantId); // Set the parameter for Applicant ID
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Applicant Details ---");
                System.out.printf("Applicant ID  : %-5d\n", rs.getInt("Applicant ID"));
                System.out.printf("First Name    : %-15s\n", rs.getString("First Name"));
                System.out.printf("Last Name     : %-15s\n", rs.getString("Last Name"));
                System.out.printf("Address       : %-15s\n", rs.getString("Address"));
                System.out.printf("Job           : %-15s\n", rs.getString("Job"));
                System.out.printf("Sex           : %-15s\n", rs.getString("Sex"));
                System.out.printf("Status        : %-15s\n", rs.getString("Status"));
            } else {
                System.out.println("No details found for Applicant ID: " + applicantId);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving applicant details: " + e.getMessage());
        }
    }
}
