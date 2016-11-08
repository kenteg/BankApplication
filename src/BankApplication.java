import com.luxoft.bankapp.domain.*;
import com.luxoft.bankapp.domain.listener.EmailNotificationListener;
import com.luxoft.bankapp.domain.listener.PrintClientListener;
import com.luxoft.bankapp.exceptions.ClientExistException;
import com.luxoft.bankapp.exceptions.IllegalArgumentException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.bankapp.service.BankService;

import java.util.Iterator;
import java.util.List;

/**
 * Created by omsk16 on 11/2/2016.
 */
public class BankApplication {
    public void modifyBank(){


    }

    public static void printBalance(Bank bank){

        List<Client> clients = bank.getClients();
        PrintClientInfo(clients);
    }

    private static void PrintClientInfo(List<Client> clients) {
        List<Account> accounts;Iterator<Client> iterCl = clients.iterator();

        while(iterCl.hasNext()) {
            Client currentClient = iterCl.next();
            accounts = currentClient.getAccounts();
            System.out.println("- - - - - - - - - - - -");
            System.out.println(currentClient.getName());
            Iterator<Account> iterAcc = accounts.iterator();
                while (iterAcc.hasNext()){
                    Account currentAcc = iterAcc.next();
                    System.out.println("Account id: " + currentAcc.getId()+ "\nbalance: " + currentAcc.getBalance()+"\nMaximum Withdraw: "+currentAcc.maximumAmountToWithdraw());

                }

        }
    }

    public static void main(String[] args) throws ClientExistException {
        Bank firstBank = new Bank();
        PrintClientListener printClientListener = new PrintClientListener();
        EmailNotificationListener emailNotificationListener = new EmailNotificationListener();
        firstBank.registerListener(printClientListener);
        firstBank.registerListener(emailNotificationListener);

        try {

            Client ivan = new Client("Ivan", Gender.MALE);
            Account ivanAcc = new SavingAccount(1, 10.0);
            ivan.addAccount(ivanAcc);
            firstBank.addClient(ivan);

            Client jack = new Client("Jack", Gender.MALE);
            Account jackAcc = new SavingAccount(2, 10.0);
            jack.addAccount(jackAcc);
            firstBank.addClient(jack);

            Client alice = new Client("Alice", Gender.FEMALE);
            Account aliceAcc = new CheckingAccount(3, 100.0, 100.0);
            alice.addAccount(aliceAcc);
            firstBank.addClient(alice);

            Client bob = new Client("Bob", Gender.MALE);
            Account bobAcc = new CheckingAccount(4, 1000.0, 100.0);
            bob.addAccount(bobAcc);
            bob.addAccount(new SavingAccount(5, 500.0));
            firstBank.addClient(bob);

            System.out.println("IvanAcc balance = " + ivanAcc.getBalance());
            bobAcc.withdraw(10000.0);

            jackAcc.deposit(777);

            Client newClient = new Client("Donald", Gender.MALE);
            newClient.addAccount(new CheckingAccount(7, 777.0, 1000.0));
            BankService.addClient(firstBank, newClient);

        }
        catch (ClientExistException ce) {
            System.out.println("Client with that name already exists!");
        }
        catch (IllegalArgumentException ia){
            System.out.println("Illegal Argument Exception");
        }
        catch (OverdraftLimitExceededException ol){
            System.out.println("Overdraft limit!");
            System.out.println("Have only: "+ol.getOverDraft());
        }
        catch (NotEnoughFundsException ne) {
            System.out.println("Not enough funds!");
            System.out.println("Have only: "+ne.getBalance());
        }


        printBalance(firstBank);

        System.out.println(emailNotificationListener.getEmailedClients());
        System.out.println(printClientListener.getPrintedClients());



    }
}
