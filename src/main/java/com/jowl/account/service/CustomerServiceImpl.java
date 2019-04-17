package com.jowl.account.service;

import com.jowl.account.model.Account;
import com.jowl.account.model.Customer;
import com.jowl.account.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The Customer Service Implementation class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> findAll() {
        Iterable<Customer> itr = customerRepository.findAll();
        return (List<Customer>) itr;
    }

    @Transactional
    public Set<Account> getCustomerAccounts(long customer_id) {
        return customerRepository.findById(customer_id).get().getAccounts();
    }
}
