package it2c.medoza.waas;

import java.util.Scanner;

public class It2cMedozaWaas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do {
            System.out.println("     --------------------------------------------------");
            System.out.println("          WELCOME TO AGENCY JOB APPLICATION SYSTEM");
            System.out.println("     --------------------------------------------------");
            System.out.println("1. APPLICANT");
            System.out.println("2. JOBS");
            System.out.println("3. APPLICATION");
            System.out.println("4. REPORTS");
            System.out.println("5 .EXIT");

            System.out.println("Enter Action:");
            int action = sc.nextInt();

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

                    break;
                case 5:

                    System.out.println("Exit Selected...type 'yes' to continue and Exit the program!!!!");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
            }

        } while (exit);
    }
}
