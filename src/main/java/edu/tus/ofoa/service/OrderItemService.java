package edu.tus.ofoa.service;

import java.util.List;
import java.util.Optional;


import edu.tus.ofoa.entity.OrderItem;

public interface OrderItemService {

    public List<OrderItem> getAllOrderItems();

    public Optional<OrderItem> getOrderItemById(Long id);

    public OrderItem createOrderItem(OrderItem OrderItem);

    public OrderItem updateOrderItem(Long id, OrderItem OrderItem);

    public void deleteOrderItem(Long id);
}
