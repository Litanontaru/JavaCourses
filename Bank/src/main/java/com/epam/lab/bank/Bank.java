package com.epam.lab.bank;

public class Bank {
    private volatile int moneyAmount;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public boolean hasMoney(int amount){
        return this.moneyAmount >= amount;
    }

    public void getMoney(int amount) {
        this.moneyAmount -= amount;
        if (this.moneyAmount < 0) {
            throw new IllegalStateException("Not enough money in bank [" + this.moneyAmount + "$]");
        }
    }
}
