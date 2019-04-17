package com.jowl.account.search;

import javax.validation.constraints.Min;

/**
 * The Account Form class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public class AccountForm {

    @Min(0)
    private long customerId;

    @Min(0)
    private double initialCredit;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "customerId=" + customerId +
                ", initialCredit=" + initialCredit +
                '}';
    }
}
