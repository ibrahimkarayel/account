package com.jowl.account.controller;

import com.jowl.account.enums.AccountName;
import com.jowl.account.enums.AccountType;
import com.jowl.account.enums.Status;
import com.jowl.account.enums.TransactionType;
import com.jowl.account.model.Account;
import com.jowl.account.model.Customer;
import com.jowl.account.model.Transaction;
import com.jowl.account.search.CustSearchCriteria;
import com.jowl.account.service.CustomerService;
import com.jowl.account.util.TestUtil;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerServiceMock;

    String getJsonOutput3 = "{\"id\":1,\"name\":\"ibrahim\",\"surname\":\"KARAYEL\",\"accounts\":[{\"id\":2,\"name\":\"DEBIT\",\"balance\":0.0,\"accountType\":\"DEBIT\",\"status\":\"ACTIVE\",\"transactions\":[{\"id\":3,\"initialCredit\":1.0,\"amount\":0.0,\"transactionType\":\"DEPOSIT\"}]},{\"id\":1,\"name\":\"CREDIT\",\"balance\":0.0,\"accountType\":\"CREDIT\",\"status\":\"ACTIVE\",\"transactions\":[]}]}";
    CustomerControllerTest() throws JSONException {
    }

    @Test
    public void testGetAll() throws Exception {
        when(customerServiceMock.findAll()).thenReturn(constructList());
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].name", is("CName 10")))
                .andExpect(jsonPath("$[0].surname", is("CSurname 10")))
                .andExpect(jsonPath("$[1].id", is(11)))
                .andExpect(jsonPath("$[1].name", is("CName 11")))
                .andExpect(jsonPath("$[1].surname", is("CSurname 11")));


        verify(customerServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(customerServiceMock);
    }

    @Test
    public void testGetCustomer() throws Exception {
        when(customerServiceMock.findById(10)).thenReturn(Optional.of(constructList().get(0)));
        mockMvc.perform(get("/customers/{id}", 10L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("CName 10")))
                .andExpect(jsonPath("$.surname", is("CSurname 10")));
    }

    @Test
    public void testGetCustomerNotFound() throws Exception {
        mockMvc.perform(get("/customers/{id}", 15L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetSearchResultCustomer() throws Exception {
        CustSearchCriteria search = new CustSearchCriteria();
        search.setName("ibrahim");
        when(customerServiceMock.findByName("ibrahim")).thenReturn(Optional.of(getOneCustomer()));
        mockMvc.perform(post("/api/search").contentType(MediaType.APPLICATION_JSON).content(TestUtil.asJsonString(search)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(content().json(getJsonOutput3));

    }

    @Test
    public void getSearchResultCustomer() {
    }

    public List<Customer> constructList() {
        List<Customer> customers = new ArrayList<>(2);
        Customer customer;
        for (int i = 0; i < 2; i++) {
            customer = new Customer();
            customer.setId((i + 10));
            customer.setName("CName " + (i + 10));
            customer.setSurname("CSurname " + (i + 10));
            customers.add(customer);
        }
        return customers;
    }

    public Customer getOneCustomer() {
        Customer customer = new Customer("ibrahim", "KARAYEL");
        customer.setId(1);

        Account account = new Account(customer, AccountName.CREDIT.name(), AccountType.CREDIT);
        account.setId(1);
        account.setBalance(0);
        account.setStatus(Status.ACTIVE);

        Account account2 = new Account(customer, AccountName.DEBIT.name(), AccountType.DEBIT);
        account2.setId(2);
        account2.setBalance(0);
        account2.setStatus(Status.ACTIVE);


        Transaction transaction = new Transaction(account);
        transaction.setId(3);
        transaction.setInitialCredit(1);
        transaction.setAmount(0);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccount(account2);

        account2.getTransactions().add(transaction);

        customer.getAccounts().add(account);
        customer.getAccounts().add(account2);

        return customer;
    }
}
