package com.jowl.account.enums;

/**
 * The Account Type Enum
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public enum AccountType {
    DEBIT(1), CREDIT(2);
    private int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
