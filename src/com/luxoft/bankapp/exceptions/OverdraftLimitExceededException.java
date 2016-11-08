package com.luxoft.bankapp.exceptions;

import com.luxoft.bankapp.domain.Account;

/**
 * Created by omsk16 on 11/7/2016.
 */
public class OverdraftLimitExceededException extends NotEnoughFundsException{
    double overDraft;
    public OverdraftLimitExceededException(Account account, double overdraft){
        super(account);
        overDraft = overdraft;
    }

    public double getOverDraft(){
        return overDraft;

    }
}
