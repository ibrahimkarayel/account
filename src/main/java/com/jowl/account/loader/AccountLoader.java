package com.jowl.account.loader;

import com.jowl.account.enums.AccountName;
import com.jowl.account.enums.AccountType;
import com.jowl.account.model.Account;
import com.jowl.account.model.Customer;
import com.jowl.account.service.AccountService;
import com.jowl.account.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Account Loader
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Component
@Order(2)
public class AccountLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AccountLoader.class);

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Accounts");
        Customer customer = customerService.findByName("ibrahim").get();
        accountService.save(new Account(customer, AccountName.CREDIT.name(), AccountType.CREDIT));
        List<Account> accountList = accountService.findAll();
        logger.info("Account counts {}", accountList.size());
        accountList.forEach(account -> logger.info("account id: {} name: {}", account.getId(), account.getName()));
    }
}
