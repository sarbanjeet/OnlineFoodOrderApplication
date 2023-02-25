package edu.tus.ofoa.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

import edu.tus.ofoa.controller.OrderController;
import edu.tus.ofoa.controller.OrderItemController;
import edu.tus.ofoa.entity.OrderItem;

public class OrderItemDto extends BaseDto {

	private String name;

	private double price;

	private int quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void toOrderItemDto(OrderItem orderItem) {
		this.setId(orderItem.getId());
		this.setQuantity(orderItem.getQuantity());
		this.setPrice(orderItem.getPrice());
		this.setName(orderItem.getName());
		
		Link selfLink = linkTo(methodOn(OrderItemController.class).getOrderItemById(orderItem.getId())).withSelfRel();
		this.add(selfLink);
	}
}
