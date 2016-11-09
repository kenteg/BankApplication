package com.luxoft.bankapp.model;

public abstract class AbstractAccount implements Account {
	protected float balance;
	protected int id;

	public AbstractAccount(int id, float balance) {
		this.id = id;
		this.balance = balance;
	}

	public int getId(){
		return id;
	}

	@Override
	public int decimalValue() {
		return Math.round(balance);
	}

	@Override
	public void printReport() {
		System.out.println("Account balance: " + balance);
	}

	@Override
	public void deposit(float x) throws IllegalArgumentException {
		if (x >= 0) {
			balance += x;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public float getBalance() {
		return balance;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractAccount that = (AbstractAccount) o;

		return id == that.id;

	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Abstract Account\n");
		sb.append("id: ");
		sb.append(id);
		sb.append("\nbalance: ");
		sb.append(balance);
		sb.append("\n");
		return sb.toString();
	}
}
