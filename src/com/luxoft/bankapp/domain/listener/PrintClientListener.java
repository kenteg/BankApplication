package com.luxoft.bankapp.domain.listener;

import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.service.ClientRegistrationListener;

/**
 * Created by omsk16 on 11/7/2016.
 */
public class PrintClientListener implements ClientRegistrationListener {
    private int printedClients;
    @Override
    public void OnClientAdded(Client c) {
        System.out.println("Client listener: " + c.getName());
        printedClients++;
    }

    public int getPrintedClients() {
        return printedClients;
    }
}
