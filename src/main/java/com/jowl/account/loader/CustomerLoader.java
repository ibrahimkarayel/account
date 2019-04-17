package com.jowl.account.loader;

import com.jowl.account.model.Customer;
import com.jowl.account.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * The Customer Loader
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@Component
@Order(1)
public class CustomerLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CustomerLoader.class);

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Customers");
        customerService.save(new Customer("ibrahim", "KARAYEL"));
        customerService.save(new Customer("Mihri", "Deniz"));
        List<Customer> customerList = customerService.findAll();
        logger.info("Customer Size: {}", customerList.size());
        customerList.forEach(customer -> logger.info("Customer Id: {} name:  {}", customer.getId(), customer.getName()));
    }
}
