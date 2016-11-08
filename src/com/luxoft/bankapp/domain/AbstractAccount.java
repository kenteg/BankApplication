package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.IllegalArgumentException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

/**
 * Created by omsk16 on 11/2/2016.
 */
 public abstract class AbstractAccount implements Account {
    protected int id;
    protected double balance;

    public AbstractAccount(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws IllegalArgumentException {
        if(amount<0) throw new IllegalArgumentException();
        balance += amount;

        message("deposit", amount);
    }

    private void message(String operation, double amount) {
        System.out.println("Successfully " + operation + ": " + amount + ". Current balance: " + balance);
    }

    public void withdraw(double amount) throws IllegalArgumentException,NotEnoughFundsException {
        if (amount<0) {
            throw new IllegalArgumentException();
        }

        if (checkBalance(amount)) {
            balance -= amount;
            message("withdrawn", amount);
        }
    }

    protected boolean checkBalance(double amount) throws NotEnoughFundsException {
        if (maximumAmountToWithdraw() < amount) {
            throw new NotEnoughFundsException(this);
        }
        return true;
    }
}

