package it2c.medoza.waas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class application {

    public void add_application() {

        Scanner sc = new Scanner(System.in);
        config conf = new config();
        applicant apl = new applicant();
        apl.viewapplicant();
        System.out.println("enter the aplicant id you want to apply: ");
        int apl_id = sc.nextInt();
        String sql = "SELECT apl_id from applicant_information where apl_id = ?";
        while (conf.getSingleValue(sql, apl_id) == 0) {
            System.out.println("applicant does not exist,please select again: ");
            apl_id = sc.nextInt();
        }
        jobs jb = new jobs();
        jb.viewjobs();

        System.out.println("enter the job id you want to apply: ");
        int job_id = sc.nextInt();
        String jsql = "SELECT job_id from jobs where job_id = ?";
        while (conf.getSingleValue(jsql, job_id) == 0) {
            System.out.println("the job id  does not exist,please select again: ");
            job_id = sc.nextInt();

        }
    }


    private void updateapplication() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID ");
        int id = sc.nextInt();

        System.out.println("ENTER NEW STATUS OF THE APPLICANT");
        String add = sc.next();

        String qry = "UPDATE application SET app_status = ? WHERE app_id = ?";

        config conf = new config();
        conf.updateRecord(qry, add, id);
    }

    private void deleteapplication() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM application WHERE app_id = ?";

        config conf = new config();
        conf.deleteRecord(qry, id);

    }

    public void Application() {
        try (Scanner sc = new Scanner(System.in)) {
            boolean exit = true;

            do {
                System.out.println("1. ADD APPLICATION");
                System.out.println("2. VIEW APPLICATION");
                System.out.println("3. UPDATE THE STATUS");
                System.out.println("4. DELETE APPLICATION");
                System.out.println("5. EXIT");

                System.out.print("Enter Action: ");
                int action;
                try {
                    action = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear the invalid input
                    continue;
                }

                switch (action) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:
                        System.out.println("Exit Selected...type 'yes' to continue to move main panel");
                        String resp = sc.next();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please select from 1 to 5.");
                }
            } while (exit);
        }
    }
}
