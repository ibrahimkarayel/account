package com.jowl.account.service;

import com.jowl.account.model.Customer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    private static List<Customer> customers;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("@BeforeAll - oneTimeSetUp");
        customers = new ArrayList<>();
        Customer customer;
        for (int i = 0; i < 5; i++) {
            customer = new Customer();
            customer.setName("TName " + i);
            customer.setSurname("TSurname " + i);
            customers.add(customer);
        }
    }

    @AfterAll
    public static void afterAll() {
        // one-time cleanup code
        customers = null;
        System.out.println("@AfterAll - oneTimeTearDown");
    }


    @Test
    public void testSave() {
        Customer customer = customerService.save(customers.get(0));
        assertNotNull(customer);
        customerService.deleteById(customer.getId());
    }

    @Test
    public void testfindAll() {
        customers.forEach(customer -> {
            int i = 0;
            customerService.save(customers.get(i++));
        });
        List<Customer> customers = customerService.findAll();
        assertTrue(customers.size() >= 5);
    }

    @Test
    public void testFindByName() {
        Customer customer = customerService.save(customers.get(2));
        boolean result=customerService.findByName(customer.getName()).isPresent();
        assertTrue(result);
        assertNotEquals(customer.getName(), customers.get(1).getName());
        assertEquals(customer.getName(), customers.get(2).getName());
        customerService.deleteById(customer.getId());

    }

    @Test
    public void TestDelete() {
        Customer customer = customerService.save(customers.get(4));
        assertNotNull(customer);
        customerService.deleteById(customer.getId());
        boolean result = customerService.findById(customer.getId()).isPresent();
        assertFalse(result);

    }


}
