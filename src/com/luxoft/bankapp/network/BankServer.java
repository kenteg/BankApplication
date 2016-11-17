package com.luxoft.bankapp.network;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.model.Client;
import com.luxoft.bankapp.service.BankService;
import com.luxoft.bankapp.service.BankServiceImpl;
import com.luxoft.bankapp.ui.BankCommander;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by omsk16 on 11/16/2016.
 */
public class BankServer {
    ServerSocket socket = null;
    BufferedWriter out = null;
    BufferedReader in = null;
    Socket bankSrv;
    BankCommander bankCommander;
    BankService bankService;
    String clientName;
    Client client;

    public BankServer() {
        bankCommander.testInitBank();
        bankCommander.createStandartCommandList();
        bankService = new BankServiceImpl();
        try {

            socket = new ServerSocket(5432);
            System.out.println("Waiting for connect...");
            bankSrv = socket.accept();
            out = new BufferedWriter(
                    new OutputStreamWriter(bankSrv.getOutputStream()));
            in = new BufferedReader(
                    new InputStreamReader(
                            bankSrv.getInputStream()));
            System.out.println("Client connected! ");
            System.out.println("Wait client name...");
            clientName = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            client = bankService.getClientByName(BankCommander.currentBank, clientName);
            bankCommander.currentClient = client;
            System.out.println(client.getName());
            if (client != null) {
                out.write("ok\n");
                out.flush();
            } else {
                out.write("error: Name not found\n");
                out.flush();
            }
        } catch (IOException e) {
            try {
                out.write("error!\n");
                out.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        String cmd;
        while (true) {
            try {
                System.out.println("Wait for command....");
                cmd = in.readLine();
                System.out.println("Command: " + cmd);
                switch (cmd) {
                    case "Balance":
                        out.write(String.valueOf((client.getBalance())) + "\n");
                        out.flush();
                        break;
                    case "Withdraw":
                        try {
                            Float f = Float.parseFloat(
                                    in.readLine());
                            client.withdraw(f);
                            out.write("ok\n");
                            out.flush();
                            System.out.println("Successfully withdrawn!" + f);
                        } catch (Exception e) {
                            out.write("error\n");
                            out.flush();
                        }
                    case "Deposit":
                        try {
                            Float f = Float.parseFloat(
                                    in.readLine());
                            client.deposit(f);
                            out.write("ok\n");
                            out.flush();
                            System.out.println("Successfully deposited!" + f);
                        } catch (Exception e) {
                            out.write("error\n");
                            out.flush();
                        }
                        break;

                    case "Exit":
                        try {
                            System.out.println("Goodbye!");
                            out.close();
                            in.close();
                            return;
                        } catch (Exception e) {
                            out.write("error\n");
                            out.flush();
                        }
                        break;
                }

            } catch (IOException e2) {
                try {
                    out.write("error!\n");
                    out.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e2.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BankServer bankServer = new BankServer();

    }
}
