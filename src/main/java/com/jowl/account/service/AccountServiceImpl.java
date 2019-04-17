package com.jowl.account.service;

import com.jowl.account.model.Account;
import com.jowl.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Account Service Implementation class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account find(long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public List<Account> findAll() {
        Iterable<Account> itr = accountRepository.findAll();
        return (List<Account>) itr;
    }

    @Override
    public List<Account> findByCustomerIdAndStatus(long customer_id, String status) {
        return accountRepository.findByCustomerId(customer_id);

    }

    @Override
    public List<Account> findByCustomerId(long customer_id) {
        return accountRepository.findByCustomerId(customer_id);

    }


}
