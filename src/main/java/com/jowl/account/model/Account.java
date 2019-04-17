package com.jowl.account.model;

import com.jowl.account.enums.AccountType;
import com.jowl.account.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Account Entity Model
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Entity
@Table(name = "account", schema = "BLUE")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @Enumerated
    @Column(columnDefinition = "smallint", name = "type")
    private AccountType accountType;

    @Enumerated
    @Column(columnDefinition = "smallint", name = "status")
    private Status status = Status.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transactions=new HashSet<>();

    public Account() {
    }

    public Account(Customer customer, String name, AccountType accountType) {
        this.customer = customer;
        this.name = name;
        this.accountType=accountType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Double.compare(account.balance, balance) == 0 &&
                Objects.equals(name, account.name) &&
                accountType == account.accountType &&
                status == account.status &&
                Objects.equals(customer, account.customer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, balance, accountType, status, customer);
    }
}
