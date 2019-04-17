package com.jowl.account.service;

import com.jowl.account.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Account Service interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Service
public interface AccountService {

    Account save(Account account);

    Account find(long id);

    List<Account> findAll();

    List<Account> findByCustomerIdAndStatus(long customer_id, String status);

    List<Account> findByCustomerId(long customer_id);
}
