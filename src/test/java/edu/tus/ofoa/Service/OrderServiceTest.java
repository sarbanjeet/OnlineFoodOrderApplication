package edu.tus.ofoa.Service;
import edu.tus.ofoa.entity.Customer;
import edu.tus.ofoa.entity.Order;
import edu.tus.ofoa.entity.OrderItem;
import edu.tus.ofoa.repository.CustomerRepository;
import edu.tus.ofoa.service.CustomerService;
import edu.tus.ofoa.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Test
    public void onlyCustomerModuleIsLoaded() {
        assertThat(customerService).isNotNull();
        assertThat(orderService).isNotNull();
    }

    @Test
    public void testCreateOrder() {
        Customer customer = new Customer("Sam", "sam@gmail.com");
        customer = customerService.createCustomer(customer);

        OrderItem orderItem1 = new OrderItem("Item 1", 2.2, 1);
        OrderItem orderItem2 = new OrderItem("Item 2", 11.0, 2);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        Order order = new Order(customer, orderItems);
        Order createdOrder = orderService.createOrder(order);
        assertEquals(order.getCustomer().getName(), createdOrder.getCustomer().getName() );
    }

    @Test
    public void testGetOrderById() {
        Customer customer = new Customer("Sam", "sam@gmail.com");
        OrderItem orderItem1 = new OrderItem("Item 1", 2.2, 1);
        OrderItem orderItem2 = new OrderItem("Item 2", 11.0, 2);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        customer = customerService.createCustomer(customer);
        Order order = new Order(customer, orderItems);
        orderService.createOrder(order);

        Optional<Order> foundOrder = orderService.getOrderById(order.getId());
        assertThat(foundOrder.isPresent());
    }


    @Test
    public void testUpdateOrder() {
        Customer customer = new Customer("Sam", "sam@gmail.com");
        OrderItem orderItem1 = new OrderItem("Item 1", 2.2, 1);
        OrderItem orderItem2 = new OrderItem("Item 2", 11.0, 2);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        customer = customerService.createCustomer(customer);
        Order order = new Order(customer, orderItems);
        orderService.createOrder(order);

        Customer newCustomer = new Customer("Sam2", "sam@gmail.com");
        newCustomer = customerService.createCustomer(newCustomer);
        Order updatedOrder = new Order(newCustomer, orderItems);
        Order savedOrder = orderService.updateOrder(order.getId(), updatedOrder);
        assertEquals(updatedOrder.getCustomer().getName(), savedOrder.getCustomer().getName());
    }

    @Test
    public void testDeleteOrder() {
        Customer customer = new Customer("Sam", "sam@gmail.com");
        OrderItem orderItem1 = new OrderItem("Item 1", 2.2, 1);
        OrderItem orderItem2 = new OrderItem("Item 2", 11.0, 2);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        customer = customerService.createCustomer(customer);
        Order order = new Order(customer, orderItems);
        orderService.createOrder(order);

        orderService.deleteOrder(order.getId());

        assertEquals(orderService.getOrderById(order.getId()), Optional.empty());
    }
}
