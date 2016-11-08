package com.luxoft.bankapp.exceptions;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Client;

/**
 * Created by omsk16 on 11/7/2016.
 */
public class NotEnoughFundsException extends BankException{
    Account acc;
    public NotEnoughFundsException(Account account){
    acc = account;
    }

    public double getBalance(){
        return acc.getBalance();
    }
}
