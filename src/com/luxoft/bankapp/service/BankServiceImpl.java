package com.luxoft.bankapp.service;

import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverDraftLimitExceededException;
import com.luxoft.bankapp.model.Account;
import com.luxoft.bankapp.model.Bank;
import com.luxoft.bankapp.model.Client;
import com.luxoft.bankapp.ui.BankCommander;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.List;

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

	@Override
	public Client getClientByName(Bank bank,String name) {
		List<Client> clients = BankCommander.currentBank.getClients();
		for (Client curClient : clients) {
			if (name.equals(curClient.getName())) {
				return curClient;
			}
		}
		return null;
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

	public void saveClient(Client c) throws IOException{
		FileOutputStream outFile = new FileOutputStream("Client.obj");
		ObjectOutputStream objOutStrm = new ObjectOutputStream(outFile);
		objOutStrm.writeObject(c);
		objOutStrm.flush();
		outFile.close();
	}

	public Client loadClient() throws IOException, ClassNotFoundException {
		FileInputStream inFile = new FileInputStream("Client.obj");
		ObjectInputStream objInStrm = new ObjectInputStream(inFile);
		return (Client) objInStrm.readObject();
	}

}
