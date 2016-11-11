package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.CommandRunException;
import com.luxoft.bankapp.model.Client;
import com.luxoft.bankapp.service.BankService;
import com.luxoft.bankapp.service.BankServiceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class FindClientCommand implements Command {


    @Override
    public void execute() throws CommandRunException {
        String clientName;
        Scanner scanClientName = new Scanner(System.in);
        System.out.println("Enter client name: ");
        clientName = scanClientName.nextLine();
        BankService bankService = new BankServiceImpl();
        Client foundClient = bankService.getClientByName(BankCommander.currentBank,clientName);
        if(foundClient!=null){
                BankCommander.currentClient = foundClient;
                System.out.println("now current client: " + foundClient.getName());
                return;
            }
                throw new CommandRunException("FindClientCommand: Client not found!");
        }


    @Override
    public void printCommandInfo(){
        System.out.println("Find Client.");
    }
}
