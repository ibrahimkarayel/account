package com.jowl.account.service;

import com.jowl.account.model.Account;
import com.jowl.account.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The Customer Service interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Service
public interface CustomerService {

    Customer save(Customer customer);

    void deleteById(long id);

    Optional<Customer> findById(long id);

    Optional<Customer>findByName(String name);

    List<Customer> findAll();

    Set<Account> getCustomerAccounts(long customer_id);
}
