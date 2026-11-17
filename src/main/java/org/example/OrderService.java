package org.example;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders(){
        return orders;
    }

    public Order createOrder(createOrderRequest request) {
        Long orderId = (long) orders.size() + 1;

        Order order = new Order(orderId, request.customer(), request.orderLines(), OrderStatus.PENDING);
        orders.add(order);
        return order;
    }

    public BigDecimal totalFor(Order orders){
        return orders.orderLines().stream()
                .map(OrderLine::lineTotal)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public Map<OrderStatus,List<Order>> orderByStatus(List<Order> orders){
        return orders.stream()
                .collect(Collectors.groupingBy(Order::status));
    }

    public Optional<Order> findById(Long Id){
        return orders.stream()
                .filter(order -> order.orderId().equals(Id))
                .findFirst();
    }
}
