package edu.tus.ofoa.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tus.ofoa.dto.CustomerDto;
import edu.tus.ofoa.entity.Customer;
import edu.tus.ofoa.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService CustomerService;


    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = "id") Long CustomerId)
            throws EntityNotFoundException {
        Customer Customer = CustomerService.getCustomerById(CustomerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found for this id :: " + CustomerId));
      
        CustomerDto dto = new CustomerDto();
        dto.toDto(Customer);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("")
    public Customer createCustomer(@Valid @RequestBody Customer Customer) {
        return CustomerService.createCustomer(Customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long CustomerId,
                                              @Valid @RequestBody Customer CustomerDetails) throws EntityNotFoundException {
        Customer updatedCustomer = CustomerService.updateCustomer(CustomerId, CustomerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable(value = "id") Long CustomerId) throws EntityNotFoundException {
       CustomerService.deleteCustomer(CustomerId);
    }
}
