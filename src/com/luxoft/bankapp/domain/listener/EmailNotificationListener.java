package com.luxoft.bankapp.domain.listener;

import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.service.ClientRegistrationListener;

/**
 * Created by omsk16 on 11/7/2016.
 */
public class EmailNotificationListener implements ClientRegistrationListener {
    private int emailedClients;
    @Override
    public void OnClientAdded(Client c) {
        System.out.println("Email listener: " + "Notification Email for Client... to be send");
        emailedClients++;
    }

    public int getEmailedClients() {
        return emailedClients;
    }
}
