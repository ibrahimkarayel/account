package com.jowl.account.service;

import com.jowl.account.enums.AccountName;
import com.jowl.account.enums.AccountType;
import com.jowl.account.model.Account;
import com.jowl.account.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {


    @Autowired
    private AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setName("TAccount");
        customer.setSurname("TAccount");
        customerService.save(customer);
        long id = customerService.findByName("TAccount").get().getId();
        List<Account> custAccounts = accountService.findByCustomerId(id);
        int s1 = custAccounts.size();
        Account account = new Account(customer, AccountName.DEBIT.name(), AccountType.DEBIT);
        accountService.save(account);
        custAccounts = accountService.findByCustomerId(1L);
        assertEquals(custAccounts.size(), s1 + 1);
        customerService.deleteById(id);

    }

    @Test
    public void testSaveWithError() {
        assertThrows(DataIntegrityViolationException.class, () -> accountService.save(new Account()));
    }

}
