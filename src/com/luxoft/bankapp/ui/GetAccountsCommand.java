package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.CommandRunException;
import com.luxoft.bankapp.model.Account;

import java.util.List;

import static com.luxoft.bankapp.ui.BankCommander.currentClient;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class GetAccountsCommand implements Command {
    public void execute() throws CommandRunException {
        if(currentClient==null){
            throw new CommandRunException("Current client not exists!");
        }
       List<Account> accounts = currentClient.getAccounts();
        for(Account acc:accounts){
            System.out.println(acc);
        }

    }
    public void printCommandInfo(){
        System.out.println("Get Accounts.");
    }
}
