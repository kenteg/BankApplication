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
    // ServerSocket socket = null;
    // BufferedWriter out = null;
    // BufferedReader in = null;
    //  Socket bankSrv;
    BankCommander bankCommander;
    BankService bankService;
    String clientName;
    Client client;

    public BankServer(Socket bankSrv) {
        bankCommander.testInitBank();
        bankCommander.createStandartCommandList();
        bankService = new BankServiceImpl();
        //try with resourses
        try (

             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(bankSrv.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(bankSrv.getInputStream()));) {
            System.out.println("i am " +Thread.currentThread().getName());
            System.out.println("Client connected! ");
            System.out.println("Wait client name...");
            clientName = in.readLine();
            client = bankService.getClientByName(BankCommander.currentBank, clientName);
            bankCommander.currentClient = client;
            System.out.println(client.getName());
            if (client != null) {
                sendOk(out);
            }
            else {
                out.write("error: Name not found\n");
                out.flush();
            }

            String cmd;
            while (true) {
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
                            remote_withdraw(out, in);
                        }
                        catch (Exception e) {
                            sendError(out);
                        }
                    case "Deposit":
                        try {
                            remoteDeposit(out, in);
                        }
                        catch (Exception e) {
                            sendError(out);
                        }
                        break;

                    case "Exit":
                        try {
                            System.out.println("Goodbye!");
                            out.close();
                            in.close();
                            return;
                        }
                        catch (Exception e) {
                            sendError(out);
                        }
                        break;
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendOk(BufferedWriter out) throws IOException {
        out.write("ok\n");
        out.flush();
    }

    private void remoteDeposit(BufferedWriter out, BufferedReader in) throws IOException {
        Float f = Float.parseFloat(
                in.readLine());
        client.deposit(f);
        sendOk(out);
        System.out.println("Successfully deposited!" + f);
    }

    private void sendError(BufferedWriter out) throws IOException {
        out.write("error\n");
        out.flush();
    }

    private void remote_withdraw(BufferedWriter out, BufferedReader in) throws IOException, NotEnoughFundsException {
        Float f = Float.parseFloat(
                in.readLine());
        client.withdraw(f);
        sendOk(out);
        System.out.println("Successfully withdrawn!" + f);
    }

    public static void main(String[] args) {
//        BankServer bankServer = new BankServer();

    }
}
