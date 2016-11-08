package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.IllegalArgumentException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

/**
 * Created by omsk16 on 11/3/2016.
 */
public interface Account {
    void deposit(double amount) throws IllegalArgumentException;
    void withdraw(double amount) throws IllegalArgumentException,NotEnoughFundsException;
    double maximumAmountToWithdraw();
    double getBalance();
    int getId();

}
