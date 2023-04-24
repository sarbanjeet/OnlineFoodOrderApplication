package edu.tus.ofoa.controller;

import edu.tus.ofoa.dto.CustomerDto;
import edu.tus.ofoa.entity.Customer;
import edu.tus.ofoa.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;
    Customer customer;
    String customerName = "Sam";
    String customerEmail = "Sam@gmail.com";
    @BeforeEach
    void setUp() {
        customer = new Customer(customerName, customerEmail);
    }

    @Test
    void getAllCustomers() {

    }

    @Test
    void getCustomerById() {
        customer = customerController.createCustomer(customer);
        ResponseEntity<CustomerDto> savedCustomer = customerController.getCustomerById(customer.getId());
        Assertions.assertTrue(savedCustomer.getBody().getName() == customerName);
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}