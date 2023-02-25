package edu.tus.ofoa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.tus.ofoa.entity.OrderItem;
import edu.tus.ofoa.repository.OrderItemRepository;


@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository OrderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return OrderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return OrderItemRepository.findById(id);
    }

    public OrderItem createOrderItem(OrderItem OrderItem) {
        return OrderItemRepository.save(OrderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem OrderItem) {
        OrderItem existingOrderItem = getOrderItemById(id).orElseThrow(() -> new EntityNotFoundException("OrderItem not found with id " + id));
        existingOrderItem.setName(OrderItem.getName());
        existingOrderItem.setPrice(OrderItem.getPrice());
        return OrderItemRepository.save(existingOrderItem);
    }

    public void deleteOrderItem(Long id) {
        OrderItemRepository.deleteById(id);
    }
}
