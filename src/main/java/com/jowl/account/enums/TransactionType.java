package com.jowl.account.enums;

/**
 * The Transaction Type Enum
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public enum TransactionType {
    PAYMENT(1),DEPOSIT(2),CASH_TRANSFER(3),INTEREST(4),;
    private int value;

    TransactionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
