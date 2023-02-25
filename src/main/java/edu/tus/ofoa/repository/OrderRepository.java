package edu.tus.ofoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tus.ofoa.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByCustomer_Id(Long id);

	
}
