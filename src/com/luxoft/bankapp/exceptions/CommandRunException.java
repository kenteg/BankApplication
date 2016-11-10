package com.luxoft.bankapp.exceptions;

/**
 * Created by omsk16 on 11/10/2016.
 */
public class CommandRunException extends Exception {
    private String message;
    public CommandRunException(String msg){
        message = msg;
    }

    public String getErrorMessage(){
        return message;
    }
}
