package com.rohit;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class customer_care {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=null;
        Socket s=null;
        DataOutputStream dout=null;
        DataInputStream din=null;
        String read="";
        String write="";
        Scanner sc = new Scanner(System.in);
            ss = new ServerSocket(5000);
            s = ss.accept();
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            while (true) {
                read = din.readUTF();
                if(read.equals("close")){
                    break;
                }
                System.out.println("USER : " + read);
                write = sc.nextLine();
                dout.writeUTF(write);
                dout.flush();

            }
        din.close();
        s.close();
        ss.close();
    }
}
