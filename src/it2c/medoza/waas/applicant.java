package it2c.medoza.waas;

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

    private void viewapplicant() {
        String appQuery = "SELECT * FROM applicant_information";
        String[] appHeaders = {"ID", "First Name", "Last Name", "Address", "Sex","status"};
        String[] appColumns = {"apl_id", "apl_fname", "apl_lname", "apl_address", "apl_sex","apl_status"};
        config conf = new config();
        conf.viewRecords(appQuery, appHeaders, appColumns);
    }

    private void updateapplicant() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID ");
        int id = sc.nextInt();
        
        System.out.println("Enter the new Address");
        String add = sc.next();

        String qry = "UPDATE applicant_information SET apl_job = ?, apl_address = ? WHERE apl_id = ?";

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
        String response;
        boolean exit = true;

        do {
            System.out.println("1. ADD APPLICANT");
            System.out.println("2. VIEW APPLICANT");
            System.out.println("3. UPDATE APPLICANT");
            System.out.println("4. DELETE APPLICANT");
            System.out.println("5. EXIT");

            System.out.println("Enter Action");
            int action = sc.nextInt();
            applicant app = new applicant();

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
                    app.viewapplicant();
                    break;
                case 4:
                    app.viewapplicant();
                    app.deleteapplicant();
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
