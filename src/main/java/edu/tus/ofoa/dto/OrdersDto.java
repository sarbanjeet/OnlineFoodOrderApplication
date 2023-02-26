package edu.tus.ofoa.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;

import edu.tus.ofoa.controller.OrderController;
import edu.tus.ofoa.entity.Order;
import edu.tus.ofoa.entity.OrderItem;

public class OrdersDto extends BaseDto {
	private double TotalPrice;
	private List<OrderItemDto> orderItems;
	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	public void toOrderDto(Order order) {
		this.setId(order.getId());
		this.setTotalPrice(order.getTotalPrice());
		this.setCreatedAt(order.getCreatedAt());
		this.setUpdatedAt(order.getUpdatedAt());

		if (order.getOrderItems() != null) {
			List<OrderItemDto> orderItemDtos = order.getOrderItems().stream().map(this::toOrderItemDto)
					.collect(Collectors.toList());
			this.setOrderItems(orderItemDtos);
		}
		Link selfLink = linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel();
		this.add(selfLink);
	}

	public OrderItemDto toOrderItemDto(OrderItem orderItem) {
		OrderItemDto dto = new OrderItemDto();
		dto.toOrderItemDto(orderItem);
		return dto;
	}
}
