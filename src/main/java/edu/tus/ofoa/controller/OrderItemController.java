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

import edu.tus.ofoa.entity.OrderItem;
import edu.tus.ofoa.service.OrderItemService;


@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable(value = "id") Long OrderItemId)
            throws EntityNotFoundException {
        OrderItem OrderItem = orderItemService.getOrderItemById(OrderItemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found for this id :: " + OrderItemId));
        return ResponseEntity.ok().body(OrderItem);
    }

    @PostMapping("")
    public OrderItem createOrderItem(@Valid @RequestBody OrderItem OrderItem) {
        return orderItemService.createOrderItem(OrderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable(value = "id") Long OrderItemId,
                                              @Valid @RequestBody OrderItem OrderItemDetails) throws EntityNotFoundException {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(OrderItemId, OrderItemDetails);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable(value = "id") Long OrderItemId) throws EntityNotFoundException {
        orderItemService.deleteOrderItem(OrderItemId);
    }
}
