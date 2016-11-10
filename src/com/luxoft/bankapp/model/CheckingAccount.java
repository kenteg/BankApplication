package com.luxoft.bankapp.model;

import com.luxoft.bankapp.exceptions.OverDraftLimitExceededException;

public class CheckingAccount extends AbstractAccount {
	private float overdraft;

	public CheckingAccount(int id, float balance,float overdraft) throws IllegalArgumentException {
		super(id,balance);
		if (overdraft >= 0) {
			this.overdraft = overdraft;
		} else {
			throw new IllegalArgumentException();
		}
	}


	public void setOverdraft(float x) throws IllegalArgumentException {
		if (x >= 0) {
			overdraft = x;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void withdraw(float x) throws OverDraftLimitExceededException {
		if(x<0) throw new IllegalArgumentException();
		if (balance + overdraft > x) {
			balance -= x;
		} else {
			throw new OverDraftLimitExceededException(getAccountName(), balance
					+ overdraft);
		}
	}

	public String getAccountName() {
		return "Checking Account";
	}

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nChecking Account\n");
        sb.append("id: ");
        sb.append(id);
        sb.append("\nbalance: ");
        sb.append(balance);
        sb.append("\noverdraft: ");
        sb.append(overdraft);
        sb.append("\n");
        return sb.toString();
    }
}
