package com.rohit;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class User extends Thread {
    String name;
    String phone_number;
    String email;
    String license;
    int type_of_cards;
    String details;
    int given_amount;
    Scanner sc = new Scanner(System.in);
    int cnt=0;
    Socket s=null;
    DataInputStream din=null;
    DataOutputStream dout=null;
    String read="";
    String write="";

    User() throws IOException {
        s =new Socket("localhost",5000);
        din=new DataInputStream(s.getInputStream()) ;
        dout = new DataOutputStream(s.getOutputStream()) ;
        System.out.println("You are logged in successfully\n Your Details are filled");
    }

    void check_challan_due(Login l) {
        try {
            FileReader fileReader = new FileReader("server.txt");
            Scanner scan = new Scanner(fileReader);
            System.out.println();
            System.out.println("Displaying your Records from database");
            System.out.println();
            for(int x=0;x<4;x++){
                System.out.println(scan.nextLine());
            }
            while (scan.hasNextLine()) {
                String newLine = scan.nextLine();
                if(newLine.isEmpty()){
                    break;
                }
                String record = newLine.substring(2, 11);
                if (record.equals(l.username)) {
                    System.out.println(newLine);
                    try {
                    int amount = Integer.parseInt(newLine.substring(76,79));
                    given_amount+=amount ;
                    }catch (Exception e) {
                        given_amount = 0;
                    }
                }
            }
            System.out.println();
            System.out.println("Your Due Amount is :"+given_amount);
            scan.close();
            if(given_amount==0){
                System.out.println("You Have no Due Penalties\n");
                System.out.println("Logging out........");
                System.out.println("--------------------------------Thank You--------------------------------");

                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void pay_challan(Login l) {
        System.out.println("The amount to be paid");
        System.out.println(given_amount);
        while(true) {
            System.out.println("Choose an option below");
            System.out.println("1.DEBIT or CREDIT CARD");
            System.out.println("2.NET BANKING");
            System.out.println("3.UPI");
            type_of_cards = sc.nextInt();
            switch (type_of_cards) {
                case 1:
                    System.out.println("Enter your card details ");
                    details = sc.next();
                    break;
                case 2:
                    System.out.println("Enter your bank details ");
                    details = sc.next();
                    break;
                case 3:
                    System.out.println("Enter your UPI pin");
                    details = sc.next();
                    break;
                default:
                    System.out.println("WRONG OPTION CHOOSEN \n Enter correct option to proceed..");
                    cnt++;
                    break;
            }
              if(type_of_cards<=3){
                  break;
              }
            if(cnt==3){
                System.out.println("--------------Limit Exceeded--------------");
                System.out.println("You have chosen wrong option more than 3 times");
                break;
            }


        }
        if(type_of_cards<=3) {
            System.out.println("\nProcessing......");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e);
            }
            LocalDateTime dt = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
            DateTimeFormatter tf = DateTimeFormatter.ISO_LOCAL_TIME;
            String myDate = dt.format(df);
            String myTime = dt.format(tf);
            try {
                FileWriter myWriter = new FileWriter("server.txt", true);
                myWriter.write("| " + l.username + "      |       -        | " + myDate + "   | " + myTime.substring(0, 8) + " |      -      |" + "Cleared" + " |\n");
                myWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Your Payment is successful");
            System.out.println("Your challans are cleared");
        }

    }
    void talk_to_customer_care() throws IOException {
        System.out.println("Type in your queries here : ");
            while (true) {
                write = sc.nextLine();

                if(write.isEmpty()){
                    continue;
                }
                dout.writeUTF(write);
                dout.flush();
                if(write.equals("close")){
                    break;
                }
                read = din.readUTF();
                System.out.println("CUSTOMER CARE : " + read);

            }
        dout.close();
        s.close();

    }

    void check_image() {
        System.out.println("Displaying the image of the violation");
        try {
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println();
        System.out.println(".......................");
        System.out.println(".                     .");
        System.out.println(".                     .");
        System.out.println(".                     .");
        System.out.println(".                     .");
        System.out.println(".                     .");
        System.out.println(".                     .");
        System.out.println(".                     .");
        System.out.println(".......................");
        System.out.println();
    }
}
