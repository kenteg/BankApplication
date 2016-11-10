package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

import java.util.Scanner;

import static com.luxoft.bankapp.ui.BankCommander.currentClient;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class DepositCommand implements Command {
    public void execute(){
        System.out.println("Enter amount to deposit: ");
        Scanner scanAmount = new Scanner(System.in);
        float amount=Float.valueOf(scanAmount.nextLine());
        try {
            currentClient.deposit(amount);
            System.out.println("Successfully deposited: "+amount);
        }
        catch (IllegalArgumentException iae) {
            System.out.println("Incorrect amount!");
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }
    public void printCommandInfo(){
        System.out.println("Deposit.");
    }
}
