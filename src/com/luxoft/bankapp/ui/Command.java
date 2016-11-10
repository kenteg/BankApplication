package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.CommandRunException;

/**
 * Created by omsk16 on 11/9/2016.
 */
public interface Command {
    void execute() throws CommandRunException;
    void printCommandInfo();

}

