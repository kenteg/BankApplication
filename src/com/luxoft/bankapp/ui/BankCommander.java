package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.CommandRunException;
import com.luxoft.bankapp.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class BankCommander {

        public static Bank currentBank = new Bank();
        public static Client currentClient;
        public static Map<String,Command> commands  = new HashMap<>();

        static Command[] commands2 = {
                new FindClientCommand(), // 1
                new GetAccountsCommand(), // 2
                new WithdrawCommand(), //3
                new DepositCommand(),  //4
                new TransferCommand(), //5
                new AddClientCommand(), //6
                // etc.
                new Command() { // 7 - Exit Command
                    public void execute() {
                        System.exit(0);
                    }

                    public void printCommandInfo() {
                        System.out.println("Exit");
                    }
                }
        };

        public static void createStandartCommandList(){
            BankCommander.registerCommand("FindClient",new FindClientCommand());
            BankCommander.registerCommand("GetAccounts",new GetAccountsCommand());
            BankCommander.registerCommand("Withdraw",new WithdrawCommand());
            BankCommander.registerCommand("Deposit",new DepositCommand());
            BankCommander.registerCommand("Transfer",new TransferCommand());
            BankCommander.registerCommand("AddClient",new AddClientCommand());
            BankCommander.registerCommand("Exit",new Command() {
                public void execute() {
                    System.exit(0);
                }
                public void printCommandInfo() {
                    System.out.println("Exit");
                }
            });

        }

        public static void registerCommand(String name, Command command){
            commands.put(name,command);
        }

        public static void removeCommand(String name){
            commands.remove(name);
        }

        public static void main(String args[]) {
            Scanner s = new Scanner(System.in);
            testInitBank();
            createStandartCommandList();

            while (true) {
                String commandString = s.nextLine();
                try {
                    commands.get(commandString).execute();
                }
                catch (CommandRunException cre){
                    System.out.println(cre.getErrorMessage());
                }
            }
        }
        public static void testInitBank(){
            try {
                Client a=new Client("Mike", 100, Gender.MALE);
                Account acc1=new SavingAccount(currentBank.generateUniqId(),1000);
                a.addAccount(acc1);
                a.setActiveAccount(acc1);
                currentBank.addClient(a);

                Client b=new Client("Donald", 100, Gender.MALE);
                Account acc3 = new SavingAccount(currentBank.generateUniqId(),2000);
                Account acc4 = new CheckingAccount(currentBank.generateUniqId(),3000,500);
                b.addAccount(acc3);
                b.addAccount(acc4);
                b.setActiveAccount(acc4);
                currentBank.addClient(b);

                Client c=new Client("Alice", 100, Gender.FEMALE);
                Account acc5 = new CheckingAccount(currentBank.generateUniqId(),100,5000);
                c.addAccount(acc5);
                c.setActiveAccount(acc5);
                currentBank.addClient(c);

                Client d=new Client("Julia", 100, Gender.FEMALE);
                currentBank.addClient(d);

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


