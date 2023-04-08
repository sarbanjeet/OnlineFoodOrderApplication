package edu.tus.ofoa.service;

import edu.tus.ofoa.entity.Order;
import edu.tus.ofoa.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id)  {
        return orderRepository.findById(id);
    }

    public List<Order> getOrderByCustomerId(Long id)  {
        return orderRepository.findByCustomer_Id(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, @NotNull Order order) throws EntityNotFoundException {
        Order existingOrder = getOrderById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setOrderItems(order.getOrderItems());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
