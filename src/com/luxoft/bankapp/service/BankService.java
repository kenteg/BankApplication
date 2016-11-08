package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.exceptions.*;


/**
 * Created by omsk16 on 11/3/2016.
 */
public class BankService {

    public static void addClient(Bank bank, Client client) throws ClientExistException {
        bank.addClient(client);
    }
}
