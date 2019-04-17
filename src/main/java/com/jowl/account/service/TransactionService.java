package com.jowl.account.service;

import com.jowl.account.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Transaction Service interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Service
public interface TransactionService {

    Transaction save(Transaction transaction);

    List<Transaction> findByAccountId(long account_id);


}
