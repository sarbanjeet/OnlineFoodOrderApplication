package edu.tus.ofoa.service;

import java.util.List;
import java.util.Optional;

import edu.tus.ofoa.entity.Order;


public interface OrderService  {

    public List<Order> getAllOrders();

    public Optional<Order> getOrderById(Long id);
    
    public List<Order> getOrderByCustomerId(Long id);

    public Order createOrder(Order order);

    public Order updateOrder(Long id, Order order) ;

    public void deleteOrder(Long id);
}
