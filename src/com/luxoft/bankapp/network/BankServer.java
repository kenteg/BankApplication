package com.luxoft.bankapp.network;

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

    public BankServer() {
        bankCommander.testInitBank();
        bankCommander.createStandartCommandList();
        bankService = new BankServiceImpl();
        try {
            socket = new ServerSocket(5432);
            bankSrv = socket.accept();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new BufferedWriter(
                    new OutputStreamWriter(bankSrv.getOutputStream()));
            in = new BufferedReader(
                    new InputStreamReader(
                            bankSrv.getInputStream()));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String clientName;
        Client client;
        try {
            while((clientName = in.readLine())!=null){
            client = bankService.getClientByName(BankCommander.currentBank, clientName);
            bankCommander.currentClient=client;
            System.out.println(client.getName());
            if (client != null) {
                out.write("ok");
            }
            else {
                out.write("error: Name not found\n");
            }
        }}
        catch (IOException e){
                try {
                    out.write("error!");
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }


            String cmd;
            try {
                cmd = in.readLine();
                bankCommander.runCommandByName(cmd);
                out.write("ok");
            }
            catch (IOException e2) {
                try {
                    out.write("error!");
                }
                catch (IOException e1) {
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
