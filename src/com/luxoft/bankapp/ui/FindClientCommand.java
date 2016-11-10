package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.CommandRunException;
import com.luxoft.bankapp.model.Client;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class FindClientCommand implements Command {


    @Override
    public void execute() throws CommandRunException {

        List<Client> clients = BankCommander.currentBank.getClients();
        String clientName;
        Scanner scanClientName = new Scanner(System.in);
        System.out.println("Enter client name: ");
        clientName = scanClientName.nextLine();


        for (Client curClient : clients) {
            if (clientName.equals(curClient.getName())) {
                BankCommander.currentClient = curClient;
                System.out.println("now current client: " + curClient.getName());
                return;
            }
        }
                throw new CommandRunException("FindClientCommand: Client not found!");
        }


    @Override
    public void printCommandInfo(){
        System.out.println("Find Client.");
    }
}
