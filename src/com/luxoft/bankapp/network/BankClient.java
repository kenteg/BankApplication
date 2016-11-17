package com.luxoft.bankapp.network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by omsk16 on 11/16/2016.
 */
public class BankClient {
    Socket socket;
    BufferedWriter out = null;
    BufferedReader in = null;

    public BankClient() {
        Scanner s = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 5432);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Enter client name: ");
            String inp = s.next();
            while (true) {
                out.write(inp+"\n");
                out.flush();
                String res = in.readLine();
                if ("ok".equals(res)) {
                    break;
                }
                else {
                    System.out.println(res);
                }
            }
            String cmd;
            while (true) {
                System.out.println("Write command  Balance, Deposit, Withdraw or Exit: ");
                cmd = s.next();
                out.write(cmd+"\n");
                out.flush();
                if((cmd.equals("Deposit"))||(cmd.equals("Withdraw"))){
                    System.out.println("Enter amount: ");
                    cmd = s.next();
                    out.write(cmd+"\n");
                    out.flush();
                }
                if("Exit".equals(cmd)){
                    System.out.println("Exit!");
                    out.close();
                    in.close();
                    return;
                }
                String res = in.readLine();
                if ("ok".equals(res)) {
                }

                else {
                    System.out.println(res);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(in.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BankClient client = new BankClient();
    }
}
