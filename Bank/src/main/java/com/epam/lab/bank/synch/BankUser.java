package com.epam.lab.bank.synch;

import com.epam.lab.bank.Bank;

public class BankUser implements Runnable {
    private static final int AMOUNT_TO_GET = 10;
    //поле должно быть final
    private Bank bank;

    public BankUser(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        boolean canContinue = true;
        while (canContinue) {
            synchronized (bank) {
                //лишнее указание this
                if (this.bank.hasMoney(AMOUNT_TO_GET)) {
                    this.bank.getMoney(AMOUNT_TO_GET);
                } else {
                    //проще сделать break
                    canContinue = false;
                }
            }
        }
    }
}
