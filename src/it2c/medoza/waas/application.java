package it2c.medoza.waas;

import java.util.Scanner;

public class application {

    public void add_application() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("enter id want to apply: ");
        String app_id = sc.next();
        System.out.println("job id avilable: ");
        String app_avi = sc.next();
        String sql = "INSERT INTO application(app_id,job_avi) VALUES (?,?)";

        conf.addRecord(sql, app_id, app_avi);

    }

    private void viewjobs() {
        String jobQuery = "SELECT * FROM jobs";
        String[] jobHeaders = {"job id", "job name", "job salary"};
        String[] jobColumns = {"job_id", "job_name", "job_salary"};
        config conf = new config();
        conf.viewRecords(jobQuery, jobHeaders, jobColumns);
    }

    private void viewapplicant() {
        String appQuery = "SELECT * FROM applicant_information";
        String[] appHeaders = {"ID", "First Name", "Last Name", "Address", "Sex", "status"};
        String[] appColumns = {"apl_id", "apl_fname", "apl_lname", "apl_address", "apl_sex", "apl_status"};
        config conf = new config();
        conf.viewRecords(appQuery, appHeaders, appColumns);

    }

    public void Application() {
        Scanner sc = new Scanner(System.in);
        String response;
        boolean exit = true;

        do {
            System.out.println("1.ADD APPLICATION");
            System.out.println("2.VIEW APPLICATION");
            System.out.println("3.UPDATE APPLICATION");
            System.out.println("4.DELETE APPLICATION");
            System.out.println("5.EXIT");

            System.out.println("Enter Action");
            int action = sc.nextInt();
            application apl = new application();

            switch (action) {

                case 1:
                    apl.viewapplicant();
                     apl.viewjobs();
                    apl.add_application();
                    
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

            }

        } while (exit);
    }
}
