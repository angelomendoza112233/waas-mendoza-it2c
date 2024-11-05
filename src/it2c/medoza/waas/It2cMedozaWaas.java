
package it2c.medoza.waas;

import java.util.Scanner;

public class It2cMedozaWaas {


    public static void main(String[] args) {
        
        
        



Scanner sc= new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("     --------------------------------------------------");
        System.out.println("          WELCOME TO AGENCY JOB APPLICATION SYSTEM");
        System.out.println("     --------------------------------------------------");
            System.out.println("                       JOB AVAILABLE");
              System.out.println("-----------------------------------------------------------------");
            System.out.println("| Dog Trainer          Web Designer       Dog Trainer            |\n");
            System.out.println("| Nursing Assistant   Project Manager   Marketing Consultant     |");
              System.out.println("-----------------------------------------------------------------");

        System.out.println("1. APPLICANT");
        System.out.println("2. APPROVALS");
        System.out.println("3. REPORTS");
        System.out.println("4 .EXIT");
        
        System.out.println("Enter Action:");
        int action = sc.nextInt();
        
        switch(action){
      case 1:
          applicant ap = new applicant();
          ap.Applicant();
            
            break;
      case 2:
            approval apr=new approval();
            apr.Approval();
            break;
      case 3:
             
            break;
            
            case 4:
                System.out.println("Exit Selected...type 'yes' to continue");
                String resp=sc.next();
                if(resp.equalsIgnoreCase("yes")){
                exit = false;
                }
                
            break;
        }
        
                
      }while(exit);
    }
}
