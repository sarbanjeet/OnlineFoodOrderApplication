package edu.tus.ofoa.entity;
import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Transactional
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",  nullable = false)
    private Customer customer;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems  = new ArrayList<OrderItem>();
    
    @Column(name = "totalPrice", nullable = false)
    private double TotalPrice;

    public Order() {}

    public Order(Customer customer, List<OrderItem> orderItems) {     
        this.customer = customer;
        this.orderItems = orderItems;       
    }
    
    @PrePersist
    public void prePersistTotalPrice() {
    	 this.TotalPrice =  orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    @PreUpdate
    public void preUpdateTotalPrice() {
    	 this.TotalPrice =  orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        //orderItem.setOrder(this);
    }
    
    // getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
