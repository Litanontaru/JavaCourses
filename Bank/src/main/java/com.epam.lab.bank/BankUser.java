package com.epam.lab.bank;

public class BankUser implements Runnable {
    private static final int AMOUNT_TO_GET = 10;
    private Bank bank;

    public BankUser(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        boolean canContinue = true;
        while (canContinue) {
            synchronized (bank){
                if (this.bank.hasMoney(AMOUNT_TO_GET)) {
                    this.bank.getMoney(AMOUNT_TO_GET);
                } else{
                    canContinue = false;
                }
            }
        }
    }
}
