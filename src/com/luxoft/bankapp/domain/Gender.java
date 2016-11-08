package com.luxoft.bankapp.domain;

/**
 * Created by omsk16 on 11/3/2016.
 */
public enum Gender{
    MALE("Mr."),FEMALE("Ms.");
    private String name;

    private Gender(String name){
    this.name=name;
    }

    public String getName() {
        return name;
    }
}
