package com.jowl.account.enums;

/**
 * The  Account Name Enum
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public enum AccountName {
    DEBIT("DEBIT"), CREDIT("CREDIT");
    private String value;

    AccountName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
