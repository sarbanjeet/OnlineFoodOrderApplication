package edu.tus.ofoa.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tus.ofoa.dto.OrdersDto;
import edu.tus.ofoa.entity.Customer;
import edu.tus.ofoa.entity.Order;
import edu.tus.ofoa.service.CustomerService;
import edu.tus.ofoa.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId) throws EntityNotFoundException {
		Order order = orderService.getOrderById(orderId)
				.orElseThrow(() -> new EntityNotFoundException("Order not found for this id :: " + orderId));
		
		OrdersDto dto = new OrdersDto();
		dto.toOrderDto(order);
		return ResponseEntity.ok().body(order);
	}

	@PostMapping("")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {

		final Order orderObj = order;
		orderObj.getOrderItems().forEach(orderItem -> orderItem.setOrder(orderObj));

		Customer customer = customerService.getCustomerById(order.getCustomer().getId())
				.orElseThrow(() -> new EntityNotFoundException("Customer not found"));
		orderObj.setCustomer(customer);

		orderService.createOrder(orderObj);

		Link selfLink = linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel();
		order.add(selfLink);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId,
			@Valid @RequestBody Order orderDetails) throws EntityNotFoundException {
		Order updatedOrder = orderService.updateOrder(orderId, orderDetails);
		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable(value = "id") Long orderId) throws EntityNotFoundException {
		orderService.deleteOrder(orderId);
	}
}
