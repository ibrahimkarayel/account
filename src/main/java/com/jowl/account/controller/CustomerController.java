package com.jowl.account.controller;

import com.jowl.account.search.AjaxResponseBody;
import com.jowl.account.model.Customer;
import com.jowl.account.search.CustSearchCriteria;
import com.jowl.account.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * The Status CustomerController class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 9/16/2018.
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            logger.info("customers: NO_CONTENT");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id ) {
        Optional<Customer> opt = customerService.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok().body(opt.get());
        }
        logger.error("customer not found id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/api/search")
    public ResponseEntity<?> getSearchResultCustomer(@Valid @RequestBody CustSearchCriteria search, Errors errors) {
        Optional<Customer> opt = customerService.findByName(search.getName());
        AjaxResponseBody<Customer> result=new AjaxResponseBody();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        if (opt.isPresent()) {
            return ResponseEntity.ok().body(opt.get());
        }else {
            result.setMsg("Customer :"+search.getName()+" not found ");
            return ResponseEntity.badRequest().body(result);
        }
    }
}
