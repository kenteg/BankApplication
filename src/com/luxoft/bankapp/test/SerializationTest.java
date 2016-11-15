package com.luxoft.bankapp.test;

import com.luxoft.bankapp.model.*;
import com.luxoft.bankapp.service.BankService;
import com.luxoft.bankapp.service.BankServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by omsk16 on 11/14/2016.
 */
public class SerializationTest {
    Client client1;
    BankService bsTest;
    Client RestoredClient1;

    @Before
    public void setUp() throws Exception {
        client1 = new Client("Boris Godunov", "bgodunov@live.com", "+79259877878", 100, Gender.MALE);
        Account acc1 = new CheckingAccount(1, 1000, 500);
        Account acc2 = new CheckingAccount(2, 2000, 500);
        Account acc3 = new CheckingAccount(3, 3000, 500);
        Account acc4 = new SavingAccount(4, 3000);
        client1.addAccount(acc1);
        client1.addAccount(acc2);
        client1.addAccount(acc3);
        client1.addAccount(acc4);

        bsTest = new BankServiceImpl();
    }

    @Test
    public void serializeTest() {
        try {
            bsTest.saveClient(client1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            RestoredClient1 = bsTest.loadClient();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        System.out.println(RestoredClient1);
        assertEquals(client1, RestoredClient1);


    }


}
