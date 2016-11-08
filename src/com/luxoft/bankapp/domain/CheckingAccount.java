package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.IllegalArgumentException;
import com.luxoft.bankapp.exceptions.*;

/**
 * Created by omsk16 on 11/3/2016.
 */
public class CheckingAccount extends AbstractAccount {
    private double overDraft;

    public CheckingAccount(int id, double balance, double overDraft) throws IllegalArgumentException {
        super(id, balance);
        if (overDraft < 0) {
            throw new IllegalArgumentException();
        }

        this.overDraft = overDraft;

    }

    public double maximumAmountToWithdraw() {

        return super.balance + this.overDraft;
    }
}
