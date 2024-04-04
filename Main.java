package com.Banking;

public class Main {
    public static void main(String[] args) {
        AccountManager.deserializeAccounts();
        System.out.println(AccountManager.accounts);
        Bank bank = new Bank();
    }
}
