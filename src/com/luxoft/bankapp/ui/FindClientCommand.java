package com.luxoft.bankapp.ui;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class FindClientCommand implements Command {

    @Override
    public void execute() {
    BankCommander.currentBank.getClients();
    }

    @Override
    public void printCommandInfo(){
        System.out.println("1. Find Client.");
    }
}
