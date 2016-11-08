package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.ClientExistException;
import com.luxoft.bankapp.service.ClientRegistrationListener;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by omsk16 on 11/2/2016.
 */

public class Bank {
    private ArrayList<Client> clients = new ArrayList<>();
    private List<ClientRegistrationListener> listeners;
    static int printedClients, emailedClients, debugedClients;

    public Bank() {
        listeners = new ArrayList<>();
        listeners.add(new DebugListener());

    }

    public void registerListener(ClientRegistrationListener listener) {
        listeners.add(listener);
    }

    public void addClient(Client client) throws ClientExistException {
        if (clients.contains(client)) {
            throw new ClientExistException();
        }

        clients.add(client);
        for (ClientRegistrationListener l : listeners) {
            l.OnClientAdded(client);
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    private static class DebugListener implements ClientRegistrationListener {
        @Override
        public void OnClientAdded(Client c) {
            LocalTime hour = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
            System.out.println(new Date() + ": " + c.getName());
            debugedClients++;
        }
    }

}
