package com.rohit;

import java.util.Scanner;

public class Login {
    String username;
    String password;
    char type;
    Scanner sc =new Scanner(System.in);
    Login() {
        System.out.println("                                                           ---------------------                                         ");
        System.out.println("                                                           | E-CHALLAN WEBSITE |                                         ");
        System.out.println("                                                           ---------------------                                         ");
        System.out.println();
        System.out.println("                                         -----------WELCOME TO THE OFFICIAL E-CHALLAN WEBSITE------------             ");
    }

    void checking_police_or_user(){
        while(true){
            System.out.println("IF YOU ARE POLICE TYPE 'P' ELSE TYPE 'U' ");

            type=sc.next().charAt(0);
            if(type=='P'||type=='U'||type=='u'||type=='p'){
                break;
            }
            else{
                System.out.println("ENTER A VALID RESPONSE");
            }
        }
    }
    void enter_user(){
        if(type=='U'||type=='u'){
            System.out.println("ENTER YOUR VEHICLE NUMBER :");
        }
        else{
            System.out.println("ENTER YOUR USERNAME : ");
        }

        username=sc.next();
    }
    void enter_password(){
        if(type=='U'||type=='u'){
            System.out.println("ENTER THE PHONE NUMBER LINKED WITH VEHICLE :");
        }
        else{
            System.out.println("ENTER YOUR PASSWORD :");
        }
        password=sc.next();

    }
    char check_type(){
        return type;

    }
    void validate(){
        System.out.println("VERIFYING........");
        try {
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }




}
