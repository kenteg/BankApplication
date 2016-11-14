import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverDraftLimitExceededException;
import com.luxoft.bankapp.model.Account;
import com.luxoft.bankapp.model.Bank;
import com.luxoft.bankapp.model.Client;
import com.luxoft.bankapp.model.Gender;
import com.luxoft.bankapp.service.BankServiceImpl;

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
        } catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds");
        }

        bankService.addAccount(client1, account1);
		
		/*
		 * Information in catch clauses are just for test purposes
		 */
        try {
            bankService.addClient(ubs, client1);
        } catch (ClientExistsException e) {
            System.out.println("Client with that name already exists!");
        }

        client1.setInitialOverdraft(1000);
        Account account2 = bankService.createAccount(client1, ubs.generateUniqId(), "Checking");
        bankService.setActiveAccount(client1, account2);
        bankService.deposit(client1, 100);
        try {
            bankService.withdraw(client1, 10500);
        } catch (OverDraftLimitExceededException e) {
            System.out.println(e.getMessage());
        } catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds!");
        }
        bankService.addAccount(client1, account2);
        //ubs.printReport();
        System.out.println(client1);
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
        } catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds");
        }
        client.addAccount(clientSaving);

        adam = new Client("Adam Budzinski", 5000, Gender.MALE);
        Account checking = adam.createAccount(bank.generateUniqId(), "Checking");
        adam.setActiveAccount(checking);
        adam.addAccount(checking);
        adam.deposit(500);

        try {
            bank.addClient(client);
        } catch (ClientExistsException e) {
            System.out.println("Client with that name already exists");
        }
        try {
            bank.addClient(adam);
        } catch (ClientExistsException e) {
            System.out.println("Client with that name already exists");
        }

    }

    public static void modifyBank(Client c, float withdraw, float deposit) {
        c.deposit(deposit);
        try {
            c.withdraw(withdraw);
        } catch (OverDraftLimitExceededException e) {
            System.out.println(e.getMessage());
        } catch (NotEnoughFundsException e) {
            System.out.println("Not enough funds");
        }
    }

    public static void printBankReport(Bank bank) {
        bank.printReport();

    }

}
