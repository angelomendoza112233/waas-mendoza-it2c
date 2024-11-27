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
    String qry = "SELECT ai.apl_id, ai.apl_fname, ai.apl_lname, j.job_name, j.job_salary, ap.app_status " +
                 "FROM applicant_information ai " +
                 "INNER JOIN application ap ON ai.apl_id = ap.apl_id " + // Join applicant_information with application on apl_id
                 "INNER JOIN jobs j ON ap.job_id = j.job_id"; // Join application with jobs on job_id

    // Column headers for the report
    String[] headers = {"Applicant ID", "First Name", "Last Name", "Job Name", "Job Salary", "Application Status"};
    String[] columns = {"apl_id", "apl_fname", "apl_lname", "job_name", "job_salary", "app_status"};

    // Call the method to display the records
    conf.viewRecords(qry, headers, columns);
}

   private void individualReport(Scanner sc) {
    config conf = new config();

    System.out.println("\n--- Individual Applicant Report ---");

    // SQL query to fetch data for an individual applicant, including job and application status
    String applicantQuery = "SELECT ai.apl_id, ai.apl_fname, ai.apl_lname, ai.apl_address, ai.apl_sex, " +
                            "j.job_name, j.job_salary, ap.app_status " +
                            "FROM applicant_information ai " +
                            "INNER JOIN application ap ON ai.apl_id = ap.apl_id " + // Join applicant_information with application
                            "INNER JOIN jobs j ON ap.job_id = j.job_id"; // Join application with jobs

    // Column headers for the individual report, including application status
    String[] applicantHeaders = {"ID", "First Name", "Last Name", "Address", "Sex", "Job Name", "Job Salary", "Application Status"};
    String[] applicantColumns = {"apl_id", "apl_fname", "apl_lname", "apl_address", "apl_sex", "job_name", "job_salary", "app_status"};

    // Call the method to display the records
    conf.viewRecords(applicantQuery, applicantHeaders, applicantColumns);

    // Get the applicant ID from the user
    int applicantId = validApplicantID(sc, conf);
    displayApplicantDetails(applicantId);

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

   private void displayApplicantDetails(int applicantId) {
       config conf = new config();
       String qry = "SELECT * FROM applicant_information WHERE apl_id = ?";
       
       System.out.println("\n--- Individual Applicant Details ---");

       try (Connection conn = conf.connectDB();
            PreparedStatement pstmt = conn.prepareStatement(qry)) {
           pstmt.setInt(1, applicantId);
           ResultSet rs = pstmt.executeQuery();

           if (rs.next()) {
               System.out.printf("Applicant ID  : %-5d\n", rs.getInt("apl_id"));
               System.out.printf("First Name    : %-15s\n", rs.getString("apl_fname"));
               System.out.printf("Last Name     : %-15s\n", rs.getString("apl_lname"));
               System.out.printf("Address       : %-15s\n", rs.getString("apl_address"));
               System.out.printf("Sex           : %-15s\n", rs.getString("apl_sex"));
           }
       } catch (SQLException e) {
           System.out.println("Error retrieving applicant details: " + e.getMessage());
       }
   }
}