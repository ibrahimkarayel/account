package com.jowl.account.enums;

/**
 * The Status Enum
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public enum Status {
    ACTIVE(1), PASSIVE(0);
    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
