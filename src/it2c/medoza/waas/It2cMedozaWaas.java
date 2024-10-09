
package it2c.medoza.waas;

import java.util.Scanner;

public class It2cMedozaWaas {
    public void add_applicant() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("id: ");
        String apl_id = sc.next();
        System.out.println("frist name: ");
        String apl_fname = sc.next();
        System.out.println("last name: ");
        String apl_lname = sc.next();
        System.out.println("applicant job: ");
        String apl_job = sc.next();
        System.out.println("applicant address: ");
        String apl_address = sc.next();
        System.out.println("applicant sex: ");
        String apl_sex = sc.next();

        String sql = "INSERT INTO applicant_information(apl_id, apl_fname, apl_lname, apl_job, apl_address, apl_sex ) VALUES (?,?,?,?,?,?)";


        conf.addRecord(sql, apl_id, apl_fname, apl_lname, apl_job, apl_address, apl_sex);

    }
    
     private void viewapplicant() {
        String votersQuery = "SELECT * FROM applicant_information";
        String[] votersHeaders = {"ID", "First Name", "Last Name","Job", "Address", "Sex"};
        String[] votersColumns = {"apl_id", "apl_fname", "apl_lname", "apl_job","apl_address", "apl_sex"};
        config conf = new config();
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }
      private void updateapplicant(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID ");
        int id = sc.nextInt();
        
        System.out.println("Enter the new Job");
        String job = sc.next();
        
        System.out.println("Enter the new Address");
        String add = sc.next();
        
        String qry = "UPDATE applicant_information SET apl_job = ?, apl_address = ? WHERE apl_id = ?";
        
        config conf = new config();
        conf.updateRecord(qry, job, add, id);
    }
    
    private void deleteapplicant(){
        
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();
        
        String qry = "DELETE FROM applicant_information WHERE apl_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);
    
    }


    public static void main(String[] args) {
        
        
        
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("1. ADD");
            System.out.println("2.VIEW");
            System.out.println("3.UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.println("Enter Action");
            int action = sc.nextInt();
            It2cMedozaWaas app = new It2cMedozaWaas();

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
                    app.deleteapplicant();
                    break;

            }

            System.out.println("continue?  (yes/no): ");
            response = sc.next();
        } while (response.equals("yes"));
        System.out.println("thank you! see you again");

    }
        
        
    }
    

