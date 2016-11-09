package com.luxoft.bankapp.service;

import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverDraftLimitExceededException;
import com.luxoft.bankapp.model.Account;
import com.luxoft.bankapp.model.Bank;
import com.luxoft.bankapp.model.Client;

public class BankServiceImpl implements BankService {

	@Override
	public void addClient(Bank bank, Client client)
			throws ClientExistsException {
		bank.addClient(client);
	}

	@Override
	public void removeClient(Bank bank, Client client) {
		bank.removeClient(client);
	}

	@Override
	public void addAccount(Client client, Account account) {
		client.addAccount(account);
	}

	@Override
	public void setActiveAccount(Client client, Account account) {
		client.setActiveAccount(account);
	}

	public Account createAccount(Client client,int id, String accountType) {

		return client.createAccount(id,accountType);
	}

	public void deposit(Client client, float x) {
		client.deposit(x);
	}

	public void withdraw(Client client, float x)
			throws NotEnoughFundsException, OverDraftLimitExceededException {
		client.withdraw(x);
	}

}
