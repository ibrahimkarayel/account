package com.jowl.account.repository;

import com.jowl.account.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The TransactionRepository class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByAccountId(long account_id);
}
