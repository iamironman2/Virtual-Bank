package com.Banking;

import java.util.Random;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Bank {
    Scanner sc = new Scanner(System.in);

    public Bank() {
        System.out.println("Welcome to Virtual Java Bank!");
        start();
    }

    private void start() {
        System.out.println("1. Create Account");
        System.out.println("2. Manage Account");
        System.out.println("3. Exit");
        System.out.println("Enter choice :");
        int choice = sc.nextInt();
        while (choice != 1 && choice != 2 && choice!=3) {
            System.out.println("Enter choice again :");
            choice = sc.nextInt();
        }
        switch (choice) {
            case 1 -> createAcc();
            case 2 -> manageAcc();
            case 3 -> exitBank();
        }
    }

    private void createAcc() {
        Account account = new Account();
        sc.nextLine();
        System.out.println("Enter account holder's name :");
        account.setName(sc.nextLine());

        System.out.println("Enter account holder's address :");
        account.setAddress(sc.nextLine());

        System.out.println("Enter account holder's mobile no. :");
        account.setMobNo(sc.nextLong());

        sc.nextLine();
        System.out.println("Enter account type - SAVINGS OR CURRENT : ");

        String accType = sc.nextLine().toUpperCase();
        while (!accType.equals("CURRENT") && !accType.equals("SAVINGS")) {
            System.out.println("Enter correct account type : ");
            accType = sc.nextLine().toUpperCase();
        }
        account.setAccType(accType);

        if (account.getAccType().equals("CURRENT")) {
            System.out.println("Enter starting balance : >2000 && <20000");
            double bal = sc.nextDouble();
            while (true) {
                if (bal > 2000) {
                    if (bal < 20000) {
                        System.out.println("Starting balance = " + bal);
                        account.setAccBal(bal);
                        break;
                    } else {
                        System.out.println("Limit exceeded! , Starting balance = 19999.9 ");
                        account.setAccBal(19999.9);
                        break;
                    }
                }
                else {
                    System.out.println("Very low amount provided! Please try again! ");
                    bal = sc.nextDouble();
                }
            }
        }
        else {
            System.out.println("Enter starting balance : >5000 && <50000");
            double bal = sc.nextDouble();
            while (true) {
                if (bal > 5000) {
                    if (bal < 50000) {
                        System.out.println("Starting balance = " + bal);
                        account.setAccBal(bal);
                        break;
                    } else {
                        System.out.println("Limit exceeded! , Starting balance = 49999.9 ");
                        account.setAccBal(49999.9);
                        break;
                    }
                }
                else {
                    System.out.println("Very low amount provided! Please try again! ");
                    bal = sc.nextDouble();
                }
            }
        }


        System.out.println("Enter 4-digit pin :");
        int pin = sc.nextInt();
        while (true) {
            if (pin >= 1000 && pin <= 9999) {
                if(AccountManager.checkPin(pin)) {
                    account.setAccPin(pin);
                    break;
                }
                else {
                    System.out.println("Pin already in use , Please provide a different 4-digit pin.");
                    pin = sc.nextInt();
                }
            }
            else {
                System.out.println("Please enter a '4-DIGIT' pin :");
                pin = sc.nextInt();
            }
        }

        account.setAccNo(Math.abs(new Random().nextLong()) % 10000000000L);
        System.out.println("Your account number is : " + account.getAccNo());
        System.out.println("Account created!\n");

        AccountManager.addAccount(account);
        manageAcc();
    }

    private void manageAcc() {
        System.out.println("1. Get Details");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Exit");
        System.out.println("Enter choice :");
        int choice = sc.nextInt();
        while(choice!= 1 && choice !=2 && choice !=3 && choice!=4 && choice!=5) {
            System.out.println("Enter correct choice :");
            choice = sc.nextInt();
        }
        switch (choice) {
            case 1 -> getDetails();
            case 2 -> withdraw();
            case 3 -> deposit();
            case 4 -> transfer();
            case 5 -> exitBank();
        }
    }

    private void getDetails() {
        System.out.println("Enter account no. and 4-digit pin : ");
        Account account = AccountManager.findAcc(sc.nextLong(), sc.nextInt());
        if(account!=null) {
            System.out.println("\nName : " + account.getName());
            System.out.println("Address : " + account.getAddress());
            System.out.println("Mobile No. : " + account.getMobNo());
            System.out.println("Account Type : " + account.getAccType());
            System.out.println("Account No. : " + account.getAccNo());
            System.out.println("Current Balance = ₹" + account.getAccBal() + "\n");
        }
        else {
            System.out.println("Incorrect account no. or pin provided!\nTry again!");
            getDetails();
        }
        start();
    }

    private void withdraw() {
        System.out.println("Enter account no. and 4-digit pin : ");
        Account account = AccountManager.findAcc(sc.nextLong(), sc.nextInt());
        if(account!=null) {
            System.out.println("Current Balance = ₹" + account.getAccBal());
            System.out.println("Enter withdrawal amount :");
            while (true) {
                double money = sc.nextDouble();
                if (account.getAccBal() - money > 0) {
                    account.setAccBal(account.getAccBal() - money);
                    AccountManager.changeBal(account);
                    System.out.println("Updated balance = ₹" + account.getAccBal() + "\n");
                    break;
                }
                else {
                    System.out.println("Not enough balance!\nPlease provide amount less than your current balance!");
                }
            }
        }
        else {
            System.out.println("Incorrect account no. or pin provided!\nTry again!");
            withdraw();
        }
        start();
    }

    private void deposit() {
        System.out.println("Enter account no. and 4-digit pin : ");
        Account account = AccountManager.findAcc(sc.nextLong(), sc.nextInt());
        if(account!=null) {
            System.out.println("\nCurrent Balance = ₹" + account.getAccBal());
            System.out.println("Enter deposit amount  (maximum limit= 1,00,000) : ");
            double money = sc.nextDouble();
            if(money<100000) {
                account.setAccBal(account.getAccBal() + money);
                AccountManager.changeBal(account);
                System.out.println("Updated balance = ₹" + account.getAccBal() + "\n");
            }
            else {
                System.out.println("Maximum limit exceeded , ₹1,00,000 deposited");
                account.setAccBal(account.getAccBal() + 100000);
                AccountManager.changeBal(account);
                System.out.println("Updated balance = ₹" + account.getAccBal() + "\n");
            }
        }
        else {
            System.out.println("Incorrect account no. or pin provided!\n");
            deposit();
        }
        start();
    }

    private void transfer(){
        System.out.println("Enter your account no. and 4 - digit pin :");
        Account account1 = AccountManager.findAcc(sc.nextLong(), sc.nextInt());
        System.out.println("Enter account no. to which the amount has to be transferred :");
        Account account2 = AccountManager.findAcc(sc.nextLong());

        if(account1!=null && account2!=null) {
            System.out.println("Your current balance = ₹"+ account1.getAccBal());
            System.out.println("Enter amount to transfer :");
            while (true) {
                double money = sc.nextDouble();
                if (account1.getAccBal() - money > 0) {
                    account1.setAccBal(account1.getAccBal() - money);
                    account2.setAccBal(account2.getAccBal() + money);
                    AccountManager.changeBal(account1);
                    AccountManager.changeBal(account2);
                    System.out.println("\nTransaction Succcessful!");
                    System.out.println("Your updated balance = ₹" + account1.getAccBal() + "\n");
                    break;
                }
                else {
                    System.out.println("Not enough balance!\n Please provide amount less than your current balance!");
                }
            }
        }
        else {
            System.out.println("Incorrect account no. or pin provided!\nTry again");
            transfer();
        }
        start();
    }

    private void exitBank() {
        System.out.println("\nThank you for visiting Java Bank!!");
        System.exit(0);
    }
}
