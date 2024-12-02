package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class applicant {

    private final Scanner sc = new Scanner(System.in); // Single Scanner instance

    public void addapplicant() {
        config conf = new config();

        String apl_id = getValidInput("ID (numbers only): ", "^[0-9]+$");
        String apl_fname = getValidInput("First Name (only letters): ", "^[a-zA-Z]{1,100}$").toLowerCase();
        String apl_lname = getValidInput("Last Name (only letters): ", "^[a-zA-Z]{1,100}$").toLowerCase();
        String apl_address = getValidInput("Address: ", "^.{1,100}$");
        String apl_sex = getValidInput("Sex (M/F): ", "^(?i)(M|F)$").toUpperCase();

        String sql = "INSERT INTO applicant_information(apl_id, apl_fname, apl_lname, apl_address, apl_sex) VALUES (?,?,?,?,?)";

        conf.addRecord(sql, apl_id, apl_fname, apl_lname, apl_address, apl_sex);
    }

    public void viewapplicant() {
        String appQuery = "SELECT * FROM applicant_information";
        String[] appHeaders = {"ID", "First Name", "Last Name", "Address", "Sex"};
        String[] appColumns = {"apl_id", "apl_fname", "apl_lname", "apl_address", "apl_sex"};
        config conf = new config();
        conf.viewRecords(appQuery, appHeaders, appColumns);
    }

    private void updateapplicant() {
        String apl_id = getValidInput("Enter Applicant ID to update (put the given applicant ID): ", "^[0-9]+$");
        String new_address = getValidInput("Enter the new Address: ", "^.{1,100}$");

        String qry = "UPDATE applicant_information SET apl_address = ? WHERE apl_id = ?";
        config conf = new config();
        conf.updateRecord(qry, new_address, apl_id);
    }

    private void deleteapplicant() {
        String apl_id = getValidInput("Enter the ID to Delete (numeric): ", "^[0-9]+$");

        String qry = "DELETE FROM applicant_information WHERE apl_id = ?";
        config conf = new config();
        conf.deleteRecord(qry, apl_id);
    }

    public void menu() {
        boolean exit = true;

        do {
            System.out.println("|--------------------------------------|");
            System.out.println("1. ADD APPLICANT");
            System.out.println("2. VIEW APPLICANT");
            System.out.println("3. UPDATE APPLICANT");
            System.out.println("4. DELETE APPLICANT");
            System.out.println("5. EXIT");

            int action = getValidInteger("Enter Action (1-5): ", 1, 5);

            switch (action) {
                case 1:
                    addapplicant();
                    break;
                case 2:
                    viewapplicant();
                    break;
                case 3:
                    viewapplicant();
                    updateapplicant();
                    break;
                case 4:
                    viewapplicant();
                    deleteapplicant();
                    break;
                case 5:
                    while (true) {
                        System.out.println("Exit Selected...type 'yes' to confirm exit or 'no' to go back to the menu:");
                        String resp = sc.nextLine().trim();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false; // Exit the program
                            break;
                        } else if (resp.equalsIgnoreCase("no")) {
                            System.out.println("Returning to the menu...");
                            break; // Return to the menu
                        } else {
                            System.out.println("Invalid input. Please type 'yes' to exit or 'no' to return to the menu.");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please select from 1 to 5.");
            }
        } while (exit);

        System.out.println("Program exited.");
    }

    private String getValidInput(String prompt, String regex) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.toLowerCase().matches(regex.toLowerCase())) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!input.toLowerCase().matches(regex.toLowerCase()));
        return input;
    }

    private int getValidInteger(String prompt, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = sc.nextInt();
                sc.nextLine(); // Consume the newline
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear invalid input
            }
        }
        return value;
    }

    public static void main(String[] args) {
        applicant app = new applicant();
        app.menu();
    }
}
