package com.epam.lab.bank.lock;

import com.epam.lab.bank.Bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrent {

    public static void main(String[] args) {
        final int bankAmount = 1000000000;
        Bank bank = new Bank(bankAmount);

        final int countOfBankUsers = 4;
        Lock lock = new ReentrantLock();
        ExecutorService service = Executors.newFixedThreadPool(countOfBankUsers);
        for (int i = 0; i < countOfBankUsers; i++) {
            service.submit(new BankUser(bank, lock));
        }
        service.shutdown();
    }
}
