package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class applicant {

    public void add_applicant() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("id: ");
        String apl_id = sc.next();
        System.out.println("frist name: ");
        String apl_fname = sc.next();
        System.out.println("last name: ");
        String apl_lname = sc.next();
        System.out.println("applicant address: ");
        String apl_address = sc.next();
        System.out.println("applicant sex: ");
        String apl_sex = sc.next();

        String sql = "INSERT INTO applicant_information(apl_id, apl_fname, apl_lname, apl_address, apl_sex ) VALUES (?,?,?,?,?)";

        conf.addRecord(sql, apl_id, apl_fname, apl_lname, apl_address, apl_sex);

    }

    public void viewapplicant() {
        String appQuery = "SELECT * FROM applicant_information";
        String[] appHeaders = {"ID", "First Name", "Last Name", "Address", "Sex"};
        String[] appColumns = {"apl_id", "apl_fname", "apl_lname", "apl_address", "apl_sex",};
        config conf = new config();
        conf.viewRecords(appQuery, appHeaders, appColumns);
    }

    private void updateapplicant() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID ");
        int id = sc.nextInt();

        System.out.println("Enter the new Address");
        String add = sc.next();  // Use nextLine() if address has spaces.

        // Correct the query by updating only the apl_address column once
        String qry = "UPDATE applicant_information SET apl_address = ? WHERE apl_id = ?";

        // Assuming config.updateRecord executes the SQL update
        config conf = new config();
        conf.updateRecord(qry, add, id);
    }

    private void deleteapplicant() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM applicant_information WHERE apl_id = ?";

        config conf = new config();
        conf.deleteRecord(qry, id);

    }

    public void Applicant() {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
            System.out.println("---------------------------------------------------");
            System.out.println("1. ADD APPLICANT");
            System.out.println("2. VIEW APPLICANT");
            System.out.println("3. UPDATE APPLICANT");
            System.out.println("4. DELETE APPLICANT");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action;
            try {
                action = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
                continue; // Go back to the start of the loop
            }

            applicant app = new applicant(); // Assuming 'applicant' is a class you have

            switch (action) {
                case 1:
                    app.add_applicant();
                    break;
                case 2:
                    app.viewapplicant();
                    break;
                case 3:
                    app.viewapplicant();
                    app.updateapplicant();
                    break;
                case 4:
                    app.viewapplicant();
                    app.deleteapplicant();
                    break;
                case 5:
                    System.out.println("Exit Selected...type 'yes' to continue to move to the main panel");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false; // Exit the loop
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please select from 1 to 5.");
            }
        } while (exit);
    }
}
