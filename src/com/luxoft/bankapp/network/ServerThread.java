package com.luxoft.bankapp.network;

import java.net.Socket;

/**
 * @author Khrishpens Viktor
 *         created Ноябрь 19 2016
 */
public class ServerThread implements Runnable {
    Socket bankSrv;;

    public ServerThread(Socket socket) {
        this.bankSrv=socket;
    }

    @Override
    public void run() {
    new BankServer(bankSrv);

    }
}
