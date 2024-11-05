
package it2c.medoza.waas;

import java.util.Scanner;

public class approval {
    
    
    private void updateapplicant() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID ");
        int id = sc.nextInt();
        System.out.println("Enter the new status");
        String stat = sc.next();

        String qry = "UPDATE applicant_information SET apl_status = ? WHERE apl_id = ?";

        config conf = new config();
        conf.updateRecord(qry, stat, id);
        
    }
    
        private void viewapplicant() {
        String appQuery = "SELECT * FROM applicant_information";
        String[] appHeaders = {"ID", "First Name", "Last Name", "Job", "Address", "Sex","status"};
        String[] appColumns = {"apl_id", "apl_fname", "apl_lname", "apl_job", "apl_address", "apl_sex","apl_status"};
        config conf = new config();
        conf.viewRecords(appQuery, appHeaders, appColumns);
        }
        
        
    public void Approval() {
        Scanner sc = new Scanner(System.in);
        String response;
        boolean exit = true;

        do {
            System.out.println("1. ADD");
            System.out.println("2.VIEW");
            System.out.println("3.UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.println("Enter Action");
            int action = sc.nextInt();
            approval apr = new approval();
            switch (action) {

                case 1:

                    
                    break;
                case 2:
                    
                    break;
                case 3:
             apr.viewapplicant();
                    apr.updateapplicant();
                    
                    break;
                case 4:
                   
                    break;
                case 5:

                    System.out.println("Exit Selected...type 'yes' to continue");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }

                    break;

            }

        } while (exit);
        

    }

    
}
