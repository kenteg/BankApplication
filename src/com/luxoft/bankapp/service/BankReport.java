package com.luxoft.bankapp.service;

import com.luxoft.bankapp.model.Account;
import com.luxoft.bankapp.model.Bank;
import com.luxoft.bankapp.model.Client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by omsk16 on 11/14/2016.
 */
public class BankReport {
    //Displays the number of bank clients
    public static int getNumberOfClients(Bank bank) {
        int count = 0;
        for (Client c : bank.getClients()) {
            count++;
        }
        return count;
    }

    //Displays the total number of accounts for all bank clients
    public static int getAccountsNumber(Bank bank) {
        int count = 0;
        for (Client c : bank.getClients()) {
            for (Account acc : c.getAccounts()) {
                count++;
            }
        }
        return count;
    }

    //Displays the list of all accounts. The list is ordered by current account balance
    public static TreeSet<Client> getClientsSorted(Bank bank) {
        ArrayList<Client> unsortedClients = (ArrayList) bank.getClients();
        TreeSet<Client> sortedClients = new TreeSet<>(new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                if (o1.getBalance() < o2.getBalance()) return 1;
                if (o1.getBalance() > o2.getBalance()) return -1;
                return 0;
            }
        });
        sortedClients.addAll(unsortedClients);
        return sortedClients;
    }

    //Display the total amount of credits granted to the bank clients. That is, the sum of all values above account balance for CheckingAccount
    public static float getBankCreditSum(Bank bank) {
        int totalCredit = 0;
        for (Client c : bank.getClients()) {
            for (Account acc : c.getAccounts()) {
                if (acc.getBalance() < 0) totalCredit += Math.abs(acc.getBalance());
            }
        }
        return totalCredit;
    }

    //Add field city to class Client. This method needs a table Map<String, List<Client>>, with cities as the keys and values – the list of clients in each city. Print the resulting table, and order by city name alphabetically.
    public static List<Client> getClientsByCity(Bank bank) {
        return null;
    }


}
