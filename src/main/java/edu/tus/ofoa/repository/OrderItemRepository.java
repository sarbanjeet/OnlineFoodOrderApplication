package edu.tus.ofoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tus.ofoa.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
