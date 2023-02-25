package edu.tus.ofoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tus.ofoa.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
