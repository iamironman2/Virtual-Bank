package com.Banking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Backend working , typically serialization work and checking , changing stuff in file is handled here!

public class AccountManager {
    static List<Account> accounts = new ArrayList<>();
    static int index;
    final static String fileName = "Filename.txt";

    static void addAccount(Account account){
        accounts.add(account);
        serializeAccounts();
    }
    static void serializeAccounts() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            for (Account account1 : accounts){
                objectOutputStream.writeObject(account1);
            }
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void deserializeAccounts()  {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            while (true) {
                Account account = (Account) objectInputStream.readObject();
                accounts.add(account);
            }
        }catch (EOFException ignored) {

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    static Account findAcc(long accNo , int pin){
        for(Account account : accounts) {
            if(account.getAccNo()==accNo && account.getAccPin()==pin) {
                index = accounts.indexOf(account);
                return account;
            }
        }
        return null;
    }
    static Account findAcc(long accNo){
        for(Account account : accounts) {
            if(account.getAccNo()==accNo) {
                index = accounts.indexOf(account);
                return account;
            }
        }
        return null;
    }

    static void changeBal(Account account){
        accounts.set(index,account);
        serializeAccounts();
    }

    static boolean checkPin(int pin){
        for (Account account : accounts){
            if(account.getAccPin()==pin)
                return false;
        }
        return true;
    }
}
