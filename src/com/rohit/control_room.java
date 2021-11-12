package com.rohit;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class control_room {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        DataInputStream din =  new DataInputStream(s.getInputStream()) ;
        String read="";
        while(true) {
            read = din.readUTF();
            if(read.equals("close")) {
                break;
            }
                System.out.println(read);

        }
        din.close();
        s.close();
        ss.close();
    }
}
