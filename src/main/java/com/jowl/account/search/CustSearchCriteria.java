package com.jowl.account.search;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The Customer Search Criteria class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public class CustSearchCriteria {

    @NotBlank(message = "Customer Name can't empty!")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
