package com.luxoft.bankapp.network;

import org.junit.Test;

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
     /*   try {
            connect();
            workWithServer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(in.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        } */
    }

    public void workWithServer() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter client name: ");
        String inp = s.next();
        while (true) {
            out.write(inp + "\n");
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
            out.write(cmd + "\n");
            out.flush();
            if ((cmd.equals("Deposit")) || (cmd.equals("Withdraw"))) {
                System.out.println("Enter amount: ");
                cmd = s.next();
                out.write(cmd + "\n");
                out.flush();
            }
            if ("Exit".equals(cmd)) {
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

    public void test() {
        try {
            while (true) {
                out.write("Donald" + "\n");
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
                cmd = "Deposit";
                out.write(cmd + "\n");
                out.flush();
                if ((cmd.equals("Deposit")) || (cmd.equals("Withdraw"))) {
                    cmd = "1.0";
                    out.write(cmd + "\n");
                    out.flush();
                }
                if ("Exit".equals(cmd)) {
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

    }

    private void connect() throws IOException {
        socket = new Socket("localhost", 5432);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static void main(String[] args) {

        BankClient mockClients[]= new BankClient[2];
        for (int i = 0; i < 100; i++) {
            try {
                mockClients[i].connect();
                mockClients[i].test();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
