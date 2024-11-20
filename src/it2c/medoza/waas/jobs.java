package it2c.medoza.waas;

import java.util.Scanner;

public class jobs {

    public void add_jobs() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("job id: ");
        String job_id = sc.next();
        System.out.println("job name: ");
        String job_name = sc.next();
        System.out.println("job salary monthly ");
        String job_salary = sc.next();

        String sql = "INSERT INTO jobs( job_id, job_name, job_salary ) VALUES (?,?,?)";

        conf.addRecord(sql, job_id, job_name, job_salary);

    }

    public void viewjobs() {
        String jobQuery = "SELECT * FROM jobs";
        System.out.println("JOB PANEL");
        String[] jobHeaders = {"job id", "job name", "job salary"};
        String[] jobColumns = {"job_id", "job_name", "job_salary"};
        config conf = new config();
        conf.viewRecords(jobQuery, jobHeaders, jobColumns);
    }

    private void updatejobs() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID ");
        int id = sc.nextInt();

        System.out.println("Enter the new salary");
        String add = sc.next();

        String qry = "UPDATE jobs SET job_salary = ? WHERE apl_id = ?";

        config conf = new config();
        conf.updateRecord(qry, add, id);
    }

    private void deletejobs() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM jobs WHERE job_id = ?";

        config conf = new config();
        conf.deleteRecord(qry, id);

    }

    public void job() {
        Scanner sc = new Scanner(System.in);
        String response;
        boolean exit = true;

        do {
            System.out.println("1. ADD JOBS");
            System.out.println("2. VIEW JOBS");
            System.out.println("3. UPDATE JOBS");
            System.out.println("4. DELETE JOBS");
            System.out.println("5. EXIT");

            System.out.println("Enter Action");
            int action = sc.nextInt();
            jobs jb = new jobs();

            switch (action) {

                case 1:

                    jb.add_jobs();

                    break;
                case 2:

                    jb.viewjobs();
                    break;
                case 3:
                    jb.updatejobs();

                    break;
                case 4:
                    jb.viewjobs();

                    jb.deletejobs();
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
