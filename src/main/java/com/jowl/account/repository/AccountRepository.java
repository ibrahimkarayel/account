package com.jowl.account.repository;

import com.jowl.account.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The AccountRepository class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    //@Query("from Account a where a.status=:status")
    Optional<Account> findByStatus(String status);

    List<Account> findByCustomerIdAndStatus(long customer_id, String status);

    List<Account> findByCustomerId(long customer_id);

 /*   @Query("from Account a where a.customer_id=:customer_id and  a.status=:status")
    List<Account> findByCustomerIdAndStatus(@Param("userId") int userId, @Param("status") String status);*/

   /* @Query("from Account t where t.id BETWEEN  :start and :end")*/

}
