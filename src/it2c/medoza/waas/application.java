package it2c.medoza.waas;

import java.util.Scanner;

public class application {

    public void add_application() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("enter the aplicant id you want to apply: ");
        String app_id = sc.next();
        System.out.println("Input applicant given name avilable: ");
        String app_name = sc.next();
        System.out.println("Input job name avilable: ");
        String job_avi = sc.next();
        System.out.println("Input status of the applicant: ");
        String app_status = sc.next();

        String sql = "INSERT INTO application(app_id ,app_name,job_avi,app_status) VALUES (?,?,?,?)";

        conf.addRecord(sql, app_id, app_name, job_avi, app_status);

    }

    private void viewjobs() {
        String jobQuery = "SELECT * FROM jobs";
        String[] jobHeaders = {"JOB AVILABLE"};
        String[] jobColumns = {"job_name"};
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

    private void viewaplication() {
        String appQuery = "SELECT * FROM application";
        String[] appHeaders = {"APPLICANT ID", "APPLICANT NAME", "JOB NAME", "STATUS"};
        String[] appColumns = {"app_id", "app_name", "job_avi", "app_status"};
        config conf = new config();
        conf.viewRecords(appQuery, appHeaders, appColumns);

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
        Scanner sc = new Scanner(System.in);
        String response;
        boolean exit = true;

        do {
            System.out.println("1.ADD APPLICATION");
            System.out.println("2.VIEW APPLICATION");
            System.out.println("3.UPDATE THE STATUS");
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
                    apl.viewaplication();

                    break;
                case 3:
                    apl.viewaplication();
                    apl.updateapplication();

                    break;
                case 4:
                    apl.viewaplication();

                    apl.deleteapplication();

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
