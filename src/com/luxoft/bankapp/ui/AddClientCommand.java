package com.luxoft.bankapp.ui;

import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.model.Client;
import com.luxoft.bankapp.model.Gender;
import com.luxoft.bankapp.service.BankService;
import com.luxoft.bankapp.service.BankServiceImpl;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by omsk16 on 11/9/2016.
 */
public class AddClientCommand implements Command {
    public void execute() {

        Client newclient = createNewClient();
        BankService bankService = new BankServiceImpl();
        try {
            bankService.addClient(BankCommander.currentBank, newclient);
        } catch (ClientExistsException cee) {
            System.out.println("Client already exists!");
        }

    }

    public void printCommandInfo() {
        System.out.println("Add Client.");
    }

    private Client createNewClient() {
        String name;
        String email;
        String mobilePhone;
        float initialOverDraft;
        Gender gender;

        name = enterAndValidateName();
        email = enterAndValidateEmail();
        mobilePhone = enterAndValidateMobilePhone();
        gender = enterAndValidateGender();
        initialOverDraft = enterInitialOverDraft();

        return new Client(name, email, mobilePhone, initialOverDraft, gender);
    }

    private float enterInitialOverDraft() {
        System.out.println("Enter Initial overdraft amount: ");
        Scanner scan = new Scanner(System.in);
        float retAmount = 0;
        boolean valid;
        do {
            String amount = scan.nextLine();
            try {
                Float.parseFloat(amount);
                valid = true;
            } catch (NumberFormatException ex) {
                valid = false;
                System.out.println("Invalid Initial overdraft value!");
            }
            if (valid) {
                retAmount = Float.parseFloat(amount);
                if (retAmount < 0) {
                    valid = false;
                    System.out.println("Initial OverDraft must be positive!");
                }
            }
        }
        while (!valid);

        return retAmount;

    }

    private String enterAndValidateName() {
        System.out.print("Please enter name: ");
        Scanner scan = new Scanner(System.in);
        String name;
        boolean valid;
        do {
            name = scan.nextLine();
            valid = name.matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$");
            if (!valid) {
                System.out.println("Invalid Name! ReEnter please!");
            }
        }
        while (!valid);
        return name;
    }

    private String enterAndValidateEmail() {
        System.out.print("Please enter correct email: ");
        Scanner scan = new Scanner(System.in);
        String emailString;
        boolean valid;
        do {
            emailString = scan.nextLine();
            Pattern pattern = Pattern.compile("^([A-Za-z\\.0-9]{2,})@([A-Za-z\\.-0-9]{2,})\\.([A-Za-z]{2,3})$");
            Matcher matcher = pattern.matcher(emailString);
            valid = matcher.matches();
            if (!valid) {
                System.out.println("Invalid Email! ReEnter please!");
            }
        }
        while (!valid);
        return emailString;
    }

    private String enterAndValidateMobilePhone() {
        System.out.print("Please enter correct mobile phone: (Example: +79871234567)");
        Scanner scan = new Scanner(System.in);
        String mobileNumber;
        boolean valid;
        do {
            mobileNumber = scan.nextLine();
            Pattern pattern = Pattern.compile("^\\+[7]{1}[0-9]{10}$");
            Matcher matcher = pattern.matcher(mobileNumber);
            valid = matcher.matches();
            if (!valid) {
                System.out.println("Invalid mobile phone number! ReEnter please!");
            }
        }
        while (!valid);
        return mobileNumber;
    }

    private Gender enterAndValidateGender() {
        System.out.print("Please enter Gender: (Example: Male or Female)");
        Scanner scan = new Scanner(System.in);
        String gender;
        boolean valid;
        do {
            gender = scan.nextLine();
            Pattern pattern = Pattern.compile("^(?:Male|Female|male|female|m|f|M|F)$");
            Matcher matcher = pattern.matcher(gender);
            valid = matcher.matches();
            if (!valid) {
                System.out.println("Invalid gender! ReEnter please!");
            }
        }
        while (!valid);

        Pattern pattern = Pattern.compile("^(?:Male|male|m|M)$");
        Matcher matcher = pattern.matcher(gender);
        if (matcher.matches()) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

}
