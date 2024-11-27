package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class It2cMedozaWaas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
            try {
                System.out.println("     --------------------------------------------------");
                System.out.println("          WELCOME TO AGENCY JOB APPLICATION SYSTEM");
                System.out.println("     --------------------------------------------------");
                System.out.println("1. APPLICANT");
                System.out.println("2. JOBS");
                System.out.println("3. APPLICATION");
                System.out.println("4. REPORTS");
                System.out.println("5. EXIT");

                System.out.print("Enter Action: ");
                
                // Check if the next input is an integer
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.nextInt(); // Clear the invalid input
                    continue; // Skip to the next iteration of the loop
                }

                int action = sc.nextInt();  // Read user action input
                sc.nextLine(); // Clear the buffer

                switch (action) {
                    case 1:
                        applicant ap = new applicant();
                        ap.Applicant();
                        break;
                    case 2:
                        jobs jb = new jobs();
                        jb.job();
                        break;
                    case 3:
                        application apl = new application();
                        apl.Application();
                        break;
                    case 4:
                        reports rps=new reports ();
                        rps.reportsMenu();
                        break;
                    case 5:
                        System.out.println("Exit Selected...type 'yes' to continue and Exit the program!!!!");
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
                sc.nextLine(); 
            }

        } while (exit);

        sc.close();  
    }
}