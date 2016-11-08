package com.luxoft.bankapp.domain;

import java.util.*;

/**
 * Created by omsk16 on 11/2/2016.
 */

public class Client {
    private String name;
    private Gender gender;
    private List<Account> accounts;

    public Client(String name, Gender gender) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {

        accounts.add(account);
    }

    public void getClientGreeting() {
        System.out.println(this.gender.getName());
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return name.equals(client.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

