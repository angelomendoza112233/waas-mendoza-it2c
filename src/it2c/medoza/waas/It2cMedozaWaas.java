
package it2c.medoza.waas;

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

               
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next();
                    continue;
                }

                int action = sc.nextInt();  
                sc.nextLine(); 
                switch (action) {
                    case 1:
                        System.out.println("|--------------------------------------|\n            APPLICANT PANEL");
                        applicant ap = new applicant();
                        ap.menu();
                       
                        break;

                    case 2:
                        System.out.println("|--------------------------------------|\n                JOB PANEL");
                        jobs jb = new jobs();
                        jb.job();
                        break;

                    case 3:
                        System.out.println("|--------------------------------------|\n            APLICATION PANEL");
                        application apl = new application();
                        apl.Application();
                        break;

                    case 4:
                        System.out.println("|--------------------------------------|\n            REPORTS PANEL");
                        reports rps = new reports();
                        rps.reportsMenu();
                        break;

                    case 5:
                        System.out.println("Exit Selected... Type 'yes' to confirm and exit the program.");
                        String resp = sc.nextLine();  
                        if (resp.equalsIgnoreCase("yes")) {
                            System.out.println("Exiting program. Goodbye!");
                            exit = false;
                        }
                        break;

                    default:
                        System.out.println("Invalid action. Please enter a number between 1 and 5.");
                        break;
                }

            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                sc.nextLine(); 
            }

        } while (exit);

        sc.close();
    }
}
