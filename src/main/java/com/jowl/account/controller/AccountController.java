package com.jowl.account.controller;

import com.jowl.account.enums.AccountName;
import com.jowl.account.enums.AccountType;
import com.jowl.account.enums.TransactionType;
import com.jowl.account.model.Account;
import com.jowl.account.model.Customer;
import com.jowl.account.model.Transaction;
import com.jowl.account.search.AccountForm;
import com.jowl.account.search.AjaxResponseBody;
import com.jowl.account.service.AccountService;
import com.jowl.account.service.CustomerService;
import com.jowl.account.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Status AccountController class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping(value = "/accounts")
    public ResponseEntity<List<Account>> getAll() {
        List<Account> customers = accountService.findAll();
        if (customers.isEmpty()) {
            logger.info("customers: NO_CONTENT");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping(value = "/accounts/{customer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getCustomer(@PathVariable(value = "customer_id") long customer_id) {
        List<Account> accounts = accountService.findByCustomerId(customer_id);
        if (!accounts.isEmpty()) {
            return ResponseEntity.ok().body(accounts);
        }
        logger.error("customer not found id: {}", customer_id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/accounts/{customer_id}/{initial_credit}")
    public ResponseEntity<Customer> makeTransaction(@PathVariable(value = "customer_id") long customer_id,
                                                    @PathVariable(value = "initial_credit") double initial_credit ) {
        Optional<Customer> customerOpt = customerService.findById(customer_id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            Account account = new Account(customer, AccountName.DEBIT.name(), AccountType.DEBIT);
            customer.getAccounts().add(account);
            try {
                if (initial_credit > 0) {
                    Transaction transaction = new Transaction(account);
                    transaction.setTransactionTime(LocalDateTime.now());
                    transaction.setInitialCredit(initial_credit);
                    transaction.setTransactionType(TransactionType.DEPOSIT);
                    account.getTransactions().add(transaction);
                    transaction.setAccount(account);
                    /*transactionService.save(transaction);*/
                }
                //save transaction within account with cascade
                accountService.save(account);
                return ResponseEntity.ok().body(customerOpt.get());
            } catch (Exception e) {
                logger.error("makeTransaction fail {}" , e.getMessage());
                throw e;
            }

        }
        logger.error("customer not found id: {}", customer_id);
        return ResponseEntity.notFound().build();

    }

    @PostMapping(value = "/accounts/open")
    public ResponseEntity<?> getSearchResultCustomer(@Valid @RequestBody AccountForm form, Errors errors) {
        Optional<Customer> opt = customerService.findById(form.getCustomerId());
        AjaxResponseBody<Customer> result = new AjaxResponseBody();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        if (opt.isPresent()) {
            ResponseEntity<Customer> responseEntity = makeTransaction(form.getCustomerId(), form.getInitialCredit());
            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
        } else {
            result.setMsg("Customer : " + form.getCustomerId() + " not found ");
            logger.info("Customer not found ID: ",  form.getCustomerId());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
