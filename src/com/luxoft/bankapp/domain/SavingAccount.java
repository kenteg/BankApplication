package com.luxoft.bankapp.domain;

/**
* Created by omsk16 on 11/3/2016.
*/
public class SavingAccount extends AbstractAccount {

    public SavingAccount(int id, double balance){
        super(id, balance);
    }

    public double maximumAmountToWithdraw(){
        return super.balance;
    }
}
