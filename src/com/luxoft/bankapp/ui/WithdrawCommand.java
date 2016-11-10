package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

import java.util.Scanner;

import static com.luxoft.bankapp.ui.BankCommander.currentClient;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class WithdrawCommand implements Command{
    public void execute(){
        System.out.println("Enter amount to withdraw: ");
        Scanner scanAmount = new Scanner(System.in);
        float amount=Float.valueOf(scanAmount.nextLine());
        try {
            currentClient.withdraw(amount);
            System.out.println("Successfully withdrawn: "+amount);
        }
        catch (NotEnoughFundsException ne) {
            System.out.println(ne.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }
    public void printCommandInfo(){
        System.out.println("Withdraw.");
    }
}
