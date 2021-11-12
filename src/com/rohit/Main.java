package com.rohit;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Login l =new Login();
        l.checking_police_or_user();
        l.enter_user();
        l.enter_password();
        l.validate();
        if(l.check_type()=='P'||l.check_type()=='p'){
            Police p =new Police();
            p.checking_vehicle();
            p.menu();
            p.current_mistake();
            p.storing_image();
            p.write_in_file();
            p.checking_challan();
            p.warning();
            p.close_server();
        }
        else {
            User u =new User();
            u.check_challan_due(l);
            System.out.println("Press Y to check the image of the Penalties ELSE Press X");
            char check;
            check=sc.next().charAt(0);
            if(check=='Y') {
                u.check_image();
            }
            System.out.println("Press Y to PAY the DUE Penalties ELSE Press X");
            check=sc.next().charAt(0);
            if(check=='Y') {
                u.pay_challan(l);
            }
            System.out.println("Having any queries?\nPress Y to contact to our customer care or press any key to ignore ");
            check =sc.next().charAt(0);
            if(check=='Y'){
                System.out.println("* Type close anytime to close the connection");
                u.talk_to_customer_care();
            }
            System.out.println("Press Y to LogOut or Press X to check the challan Details Again");
            check=sc.next().charAt(0);
            if(check=='X') {
                u.check_challan_due(l);
            }
            System.out.println();
            System.out.println("HAVE A GOOD DAY :)");

        }
    }
}
