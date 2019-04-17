package com.jowl.account.repository;

import com.jowl.account.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The CustomerRepository class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByName(String name);

    Optional<Customer> findById(long id);
}
