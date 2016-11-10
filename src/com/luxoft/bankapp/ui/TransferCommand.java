package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.CommandRunException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.model.Client;

import java.util.List;
import java.util.Scanner;

import static com.luxoft.bankapp.ui.BankCommander.currentClient;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class TransferCommand implements Command{
    public void execute() throws CommandRunException {
        List<Client> clients = BankCommander.currentBank.getClients();
        String clientName;
        Scanner scanClientName = new Scanner(System.in);
        System.out.println("Enter recipient client name: ");
        clientName = scanClientName.nextLine();


        Client recipient=null;
        for (Client curClient : clients) {
            if (clientName.equals(curClient.getName())) {
                recipient = curClient;
                System.out.println("Recipient client: " + curClient.getName());
            }
        }
        if(recipient==null) {
            throw new CommandRunException("TransferClientCommand: Receiver client not found!");
        }

        System.out.println("Enter transfer amount: ");
        Scanner scanAmount = new Scanner(System.in);
        float amount=Float.valueOf(scanClientName.nextLine());
        transfer(amount,recipient);

    }

    private boolean transfer(float amount, Client recipient) {
        try {
            currentClient.withdraw(amount);
        }
        catch (NotEnoughFundsException ne) {
            return false;
        }

        recipient.deposit(amount);
        return true;

    }


    public void printCommandInfo(){
        System.out.println("Transfer.");
    }
}
