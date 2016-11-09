package com.luxoft.bankapp.model;

/**
 * Created by omsk16 on 11/9/2016.
 */
public enum Gender {
    MALE("Mr"), FEMALE("Ms");

    private String prefix;

    String getSalutation() {
        return prefix;
    }

    Gender(String prefix) {
        this.prefix = prefix;
    }

}
