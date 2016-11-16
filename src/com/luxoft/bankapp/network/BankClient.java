package com.luxoft.bankapp.network;

import java.io.*;
import java.net.Socket;

/**
 * Created by omsk16 on 11/16/2016.
 */
public class BankClient {
    Socket socket;
    BufferedWriter out = null;
    BufferedReader in = null;
   public BankClient(){
       try {
           socket = new Socket("localhost", 5432);
           out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       } catch (IOException e) {
           e.printStackTrace();
       }

       try {
           String line;
           while((line=in.readLine())!=null) {
                System.out.println(line);
           };

       } catch (IOException e) {
           e.printStackTrace();
       }

       try {
           System.out.println(in.readLine());
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public static void main(String[] args) {
        BankClient client = new BankClient();
    }
}
