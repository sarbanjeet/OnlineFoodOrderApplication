package edu.tus.ofoa.Service;

import edu.tus.ofoa.entity.Customer;
import edu.tus.ofoa.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
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
        customer = customerService.createCustomer(customer);
        Optional<Customer> savedCustomer = customerService.getCustomerById(customer.getId());
        Assertions.assertTrue(savedCustomer.isPresent() && savedCustomer.get().getName() == customerName);
    }

    @Test
    void createCustomer() {
        customer = customerService.createCustomer(customer);
        Assertions.assertTrue(customer.getName() == customerName);
    }

    @Test
    void updateCustomer() {
        customer = customerService.createCustomer(customer);

        String changedName = "Harry";
        customer.setName(changedName);
        customer = customerService.updateCustomer(customer.getId(), customer);
        Assertions.assertTrue(customer.getName() == changedName);
    }

    @Test
    void deleteCustomer() {
        customer = customerService.createCustomer(customer);
        customerService.deleteCustomer(customer.getId());

        Assertions.assertTrue(!customerService.getCustomerById(customer.getId()).isPresent());
    }
}