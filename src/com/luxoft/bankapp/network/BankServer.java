package com.luxoft.bankapp.network;

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
    public BankServer(){
        try {
            socket = new ServerSocket(5432);
            bankSrv=socket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new BufferedWriter(
          new OutputStreamWriter(bankSrv.getOutputStream()));
            in  = new BufferedReader(
                    new InputStreamReader(
                            bankSrv.getInputStream()));
            out.write(bankCommander.createStandartCommandList());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        bankCommander.testInitBank();
        String cmd;
        try {
            out.write("Enter Command:");
            out.flush();
            cmd = in.readLine();
            bankCommander.runCommandByName(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        BankServer bankServer = new BankServer();

    }
}
