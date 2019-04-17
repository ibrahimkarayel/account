package com.jowl.account.service;

import com.jowl.account.model.Transaction;
import com.jowl.account.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Transaction Implementation class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByAccountId(long account_id) {
        return transactionRepository.findByAccountId(account_id);
    }
}
