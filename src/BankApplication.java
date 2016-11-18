import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverDraftLimitExceededException;
import com.luxoft.bankapp.gui.BankApplicationForm;
import com.luxoft.bankapp.model.Account;
import com.luxoft.bankapp.model.Bank;
import com.luxoft.bankapp.model.Client;
import com.luxoft.bankapp.model.Gender;
import com.luxoft.bankapp.service.BankReport;
import com.luxoft.bankapp.service.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static junit.framework.TestCase.assertEquals;

public class BankApplication {
    static Client client;
    static Client adam;
    static Bank bank = new Bank();


    public static void main(String[] args) {

        initialize(bank);
        modifyBank(client, 0, 500);
        modifyBank(adam, 20, 0);
        printBankReport(bank);
        System.out.println(adam);

        // Initialization using BankService implementation

        BankServiceImpl bankService = new BankServiceImpl();
        Bank ubs = new Bank();

        Client client1 = new Client("Anna Smith", Gender.FEMALE);
        client1.setInitialBalance(1000);
        Account account1 = bankService.createAccount(client1, ubs.generateUniqId(), "Saving");
        bankService.setActiveAccount(client1, account1);
        bankService.deposit(client1, 400);

		/*
         * Information in catch clauses are just for test purposes
		 */
        try {
            bankService.withdraw(client1, 50);
        }
        catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds");
        }

        bankService.addAccount(client1, account1);
		
		/*
		 * Information in catch clauses are just for test purposes
		 */
        try {
            bankService.addClient(ubs, client1);
        }
        catch (ClientExistsException e) {
            System.out.println("Client with that name already exists!");
        }

        client1.setInitialOverdraft(1000);
        Account account2 = bankService.createAccount(client1, ubs.generateUniqId(), "Checking");
        bankService.setActiveAccount(client1, account2);
        bankService.deposit(client1, 100);
        try {
            bankService.withdraw(client1, 10500);
        }
        catch (OverDraftLimitExceededException e) {
            System.out.println(e.getMessage());
        }
        catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds!");
        }
        bankService.addAccount(client1, account2);
        //ubs.printReport();
        System.out.println(client1);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new BankApplicationForm();
            }
        });
    }

    /*
     * Method that creates a few clients and initializes them with sample values
     */

    public static void initialize(Bank bank) {
        client = new Client("Jonny Bravo", 1000, Gender.MALE);
        Account clientSaving = client.createAccount(bank.generateUniqId(), "Saving");
        client.setActiveAccount(clientSaving);
        try {
            client.withdraw(100);
        }
        catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds");
        }
        client.addAccount(clientSaving);

        adam = new Client("Adam Budzinski", 5000, Gender.MALE);
        Account checking = adam.createAccount(bank.generateUniqId(), "Checking");
        adam.setActiveAccount(checking);
        adam.addAccount(checking);
        adam.deposit(500);
        try {
            adam.withdraw(3500);
        }
        catch(NotEnoughFundsException ne){
            ne.printStackTrace();
        }

        Client boris = new Client("Boris Ivanov", 5000, Gender.MALE);
        Account checking1 = boris.createAccount(bank.generateUniqId(), "Checking");
        boris.setActiveAccount(checking1);
        boris.addAccount(checking1);
        boris.deposit(6000);

        Client zafira = new Client("Zafira Budzinski", 5000, Gender.FEMALE);
        Account checking2 = zafira.createAccount(bank.generateUniqId(), "Checking");
        Account checking3 = zafira.createAccount(bank.generateUniqId(), "Checking");
        Account saving2 = zafira.createAccount(bank.generateUniqId(), "Saving");
        zafira.setActiveAccount(checking2);
        zafira.addAccount(checking2);
        zafira.addAccount(checking3);
        zafira.addAccount(saving2);
        try {
            zafira.withdraw(3500);
        }
        catch(NotEnoughFundsException ne){
            ne.printStackTrace();
        }


        try {
            bank.addClient(client);
        }
        catch (ClientExistsException e) {
            System.out.println("Client with that name already exists");
        }
        try {
            bank.addClient(adam);
        }
        catch (ClientExistsException e) {
            System.out.println("Client with that name already exists");
        }
        try {
            bank.addClient(boris);
        }
        catch (ClientExistsException e) {
            System.out.println("Client with that name already exists");
        }
        try {
            bank.addClient(zafira);
        }
        catch (ClientExistsException e) {
            System.out.println("Client with that name already exists");
        }

    }

    public static void modifyBank(Client c, float withdraw, float deposit) {
        c.deposit(deposit);
        try {
            c.withdraw(withdraw);
        }
        catch (OverDraftLimitExceededException e) {
            System.out.println(e.getMessage());
        }
        catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds!");
        }
    }

    public static void printBankReport(Bank bank) {
        bank.printReport();

    }

    @Test
    public void bankReportTest() {
        initialize(bank);
        assertEquals(4, BankReport.getNumberOfClients(bank));
        assertEquals(6, BankReport.getAccountsNumber(bank));
        assertEquals(6500.0,BankReport.getBankCreditSum(bank),0.001);
        System.out.println(BankReport.getClientsSorted(bank));

    }

}
