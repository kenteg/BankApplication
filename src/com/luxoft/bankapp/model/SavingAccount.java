package com.luxoft.bankapp.model;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

public class SavingAccount extends AbstractAccount {

	public SavingAccount(int id,float initialBalance) throws IllegalArgumentException {
		super(id,initialBalance);
		if (initialBalance >= 0) {
			balance = initialBalance;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void withdraw(float x) throws NotEnoughFundsException {
		if (balance >= x)
			balance -= x;
		else
			throw new NotEnoughFundsException(x);
	}

	public String getAccountName() {
		return "Saving Account";
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.replace(0,17,"\nSaving Account\n");
		return sb.toString();
	}
}
