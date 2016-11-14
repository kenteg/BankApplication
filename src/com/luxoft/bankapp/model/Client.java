package com.luxoft.bankapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

public class Client implements Report {
	private String name;
	private Set<Account> accounts = new HashSet<Account>();
	
	private Account activeAccount;
	private float initialOverdraft;
	private float initialBalance;
	private Gender gender;
    private String email;
    private String mobilePhone;


	public String getGender() { return gender==Gender.MALE?"m":"f"; }
	
	public Client(String name, float initialOverdraft, Gender gender) {
		this.name = name;
		this.initialOverdraft = initialOverdraft;
		this.gender = gender;
	}

    public Client(String name, String email, String mobilePhone, float initialOverdraft, Gender gender) {
        this.name = name;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.initialOverdraft=initialOverdraft;
        this.gender = gender;

    }

    public Client(String name, Gender gender) {
		this(name, 0, gender);
	}

	public float getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(float initialBalance) {
		this.initialBalance = initialBalance;
	}

	public void setInitialOverdraft(float initialOverdraft) {
		this.initialOverdraft = initialOverdraft;
	}

	public void setActiveAccount(Account activeAccount) {
		this.activeAccount = activeAccount;
	}

	public String getName() {
		return this.name;
	}

	public float getBalance() {
		return activeAccount.getBalance();

	}

	public List<Account> getAccounts() {
        List<Account> getAccounts = new ArrayList<>();
        getAccounts.addAll(accounts);
		return getAccounts;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public void deposit(float x) throws IllegalArgumentException {
		activeAccount.deposit(x);
	}

	public void withdraw(float x) throws NotEnoughFundsException,IllegalArgumentException {
		activeAccount.withdraw(x);
	}

	public Account createAccount(int id,String accountType) {
		switch (accountType) {
			case "Saving": {
				return new SavingAccount(id,initialBalance);
			}
			case "Checking": {
				return new CheckingAccount(id,initialBalance,initialOverdraft);
			}
			default: {
				return null;
			}
		}
	}

	public void printReport() {
		System.out.println("Name : " + this.getClientSalutation() + " " + name);
		for (Account a : accounts) {
			System.out.print(a.getAccountName() + " balance: " + a.getBalance()
					+ " ");
		}
		System.out.println();

	}

	public String getClientSalutation() {
		return gender.getSalutation();
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return name.equals(client.name) && gender == client.gender;

    }

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + gender.hashCode();
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Client:");
		sb.append("\nname: ");
		sb.append(name);
		sb.append("\ngender: ");
		sb.append(getGender());
		sb.append("\nAccounts: ");
		sb.append(accounts);
		sb.append("\n");
		return sb.toString();

	}
}
