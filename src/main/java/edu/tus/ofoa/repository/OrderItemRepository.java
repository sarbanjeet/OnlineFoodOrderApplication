package edu.tus.ofoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tus.ofoa.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
