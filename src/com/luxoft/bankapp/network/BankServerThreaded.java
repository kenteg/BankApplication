package com.luxoft.bankapp.network;

import com.luxoft.bankapp.model.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Khrishpens Viktor
 *         created Ноябрь 19 2016
 */
public class BankServerThreaded {
    public static final int POOL_SIZE = 100;
    public static final int PORT = 5432;
    ServerSocket socket;
    boolean running = false;

    public BankServerThreaded() {
        try (ServerSocket socket = new ServerSocket(PORT)) {

            ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
            running = true;
            while (running) {
                Socket clientSocket = null;
                clientSocket = socket.accept();
                pool.execute(new ServerThread(clientSocket));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    BankServerThreaded bank = new BankServerThreaded();
    }
}


