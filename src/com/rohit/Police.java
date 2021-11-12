package com.rohit;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Police extends values   {
    DataInputStream din;
    DataOutputStream dout;
    Socket s;
    Scanner sc = new Scanner(System.in);
    int option;
    int current_penalty_cost;
    int total_penalty_value;
    String mistake;
    String image_police;
    String vehicle_number;
    Police() throws IOException {
         s =new Socket("localhost",3000);
         din=new DataInputStream(s.getInputStream()) ;
         dout = new DataOutputStream(s.getOutputStream()) ;
        System.out.println("\nYou are logged in successfully\n Your Details are filled");
    }
    void current_mistake() throws IOException {
        System.out.println("Choose an option from the above table");
        while (true) {
            option= sc.nextInt();
            if(option<=penalties.length){
                break;
            }
            else {
                System.out.println("Kindly enter a valid mistake");
            }
        }
        mistake=penalties[option-1];
        dout.writeUTF("Traffic Rule Violated : "+mistake);
        System.out.println("Traffic Rule Violated : "+mistake);
        current_penalty_cost = fine[option-1];
        dout.writeUTF("Penalty Cost : "+ current_penalty_cost);
        System.out.println("Penalty Cost : "+ current_penalty_cost);
        try {
            FileReader fileReader = new FileReader("server.txt");
            Scanner scan = new Scanner(fileReader);
            System.out.println();
            System.out.println("Displaying your Records from database");
            System.out.println();
            while (scan.hasNextLine()) {
                String newLine = scan.nextLine();
                if(newLine.isEmpty()){
                    break;
                }
                String recordUserId = newLine.substring(2,11);
                if (recordUserId.equals(vehicle_number)) {
                    String due = newLine.substring(18, 33);
                    String bill = newLine.substring(76,79);
                    if(bill.equals("lea")) {
                        total_penalty_value = 0;
                    }
                    else {
                        try{
                    int amount = Integer.parseInt(bill);
                    System.out.println(due + " - " + amount);
                    total_penalty_value+=amount ;
                        }catch (Exception ex){
                            total_penalty_value=0;
                            System.out.println("This Customers Previous Bills are cleared");
                        }
                    }
                }
            }
            System.out.println(" "+ mistake+"- "+current_penalty_cost);
            System.out.println();
            scan.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        total_penalty_value+=current_penalty_cost;
    }
    void checking_challan(){
        System.out.println("Fine to be paid: "+total_penalty_value);
    }
    void menu(){
        System.out.println("______________________________");
        System.out.println("|S.no|    Penalties    | Fine|");
        System.out.println("------------------------------");
        for (int i=0;i<penalties.length;i++) {
            int k=i+1;
            System.out.println("|  "+ k+ " | "+penalties[i]+" | "+fine[i]+" |");
        }
        System.out.println("------------------------------");
    }
    void checking_vehicle() throws IOException {
        System.out.println("Enter the Vehicle Number (No spaces) :");
        vehicle_number=sc.next();
        dout.writeUTF("Vehicle Number : "+vehicle_number);
        System.out.println("The Details of caught Vehicle are searched in the database\n");
        try {
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    void storing_image(){
        System.out.println("Image of the mistake is Taken and stored in database");
        image_police="sample.png";
    }
    void warning(){
        if(total_penalty_value>0)
        {
            System.out.println("\nTHE VEHICLE HAS DUE PENALTIES!!!!");
        }
    }
    void write_in_file(){
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter tf = DateTimeFormatter.ISO_LOCAL_TIME;
        String myDate = dt.format(df);
        String myTime = dt.format(tf);
        try{
            FileWriter myWriter = new FileWriter("server.txt",true);
            myWriter.write("| "+vehicle_number+"      | "+mistake+"| "+myDate+"   | "+myTime.substring(0,8)+" | "+image_police+"  | "+current_penalty_cost+"    |\n");
            myWriter.close();
        }catch(Exception ex){
            System.out.println("File not Found");
            ex.printStackTrace();
        }
    }
    void close_server() throws IOException {
        dout.writeUTF("close");
        dout.close();
        s.close();
    }

}
