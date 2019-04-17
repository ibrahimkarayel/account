package com.jowl.account.model;

import com.jowl.account.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Transaction Entity Model
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Entity
@Table(name = "transaction", schema = "BLUE")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "initial_credit")
    private double initialCredit;

    @Column(name = "amount")
    private double amount;

    @Enumerated
    @Column(columnDefinition = "smallint", name = "type")
    private TransactionType transactionType;

    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionTime;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;


    public Transaction() {
    }

    public Transaction(Account account) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Double.compare(that.initialCredit, initialCredit) == 0 &&
                Double.compare(that.amount, amount) == 0 &&
                transactionType == that.transactionType &&
                Objects.equals(transactionTime, that.transactionTime) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, initialCredit, amount, transactionType, transactionTime, account);
    }
}
