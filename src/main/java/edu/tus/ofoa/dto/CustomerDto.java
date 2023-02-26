package edu.tus.ofoa.dto;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;

import edu.tus.ofoa.controller.CustomerController;
import edu.tus.ofoa.entity.Customer;
import edu.tus.ofoa.entity.Order;

public class CustomerDto extends BaseDto {
    private Long id;
    private String email;
    private String name;
    private List<OrdersDto> orders;
    private List<Link> orderLinks;

    // getters and setters

    public void addOrder(OrdersDto orderDto) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(orderDto);
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrdersDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersDto> orders) {
		this.orders = orders;
	}

	public void addOrderLink(Link link) {
        if (orderLinks == null) {
            orderLinks = new ArrayList<>();
        }
        orderLinks.add(link);
    }
    
    public void toDto(Customer customer) {
      
        this.setId(customer.getId());
        this.setName(customer.getName());
        this.setEmail(customer.getEmail());
		this.setCreatedAt(customer.getCreatedAt());
		this.setUpdatedAt(customer.getUpdatedAt());

        if (customer.getOrders() != null) {
			List<OrdersDto> orderDtos = customer.getOrders().stream()
                    .map(this::toOrderDto)
                    .collect(Collectors.toList());
			this.setOrders(orderDtos);
        }
        
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel();
		this.add(selfLink);
    }

	 public OrdersDto toOrderDto(Order order) {
	        OrdersDto dto = new OrdersDto();
			dto.toOrderDto(order);
	        return dto;
	    }
}