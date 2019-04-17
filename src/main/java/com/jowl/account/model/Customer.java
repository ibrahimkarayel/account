package com.jowl.account.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * The Customer Entity Model
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Entity
@Table(name = "customer", schema = "BLUE")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

    public Customer() {

    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, surname);
    }
}
