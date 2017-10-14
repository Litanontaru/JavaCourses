package com.epam.lab.bank;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int bankAmount = 1000000000;
        Bank bank = new Bank(bankAmount);

        final int countOfBankUsers = 4;
        List<Thread> threadPool = new ArrayList<Thread>();
        for (int i = 0; i < countOfBankUsers; i++) {
            threadPool.add(new Thread(new BankUser(bank)));
        }

        for (Thread thread : threadPool) {
            thread.start();
        }
    }
}
