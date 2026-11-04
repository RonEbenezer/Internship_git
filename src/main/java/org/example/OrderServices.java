package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServices {
    private final List<Order> orders;

    public OrderServices(List<Order> orders){
        this.orders=orders;
    }

    public BigDecimal findTot(Order orders){
        return orders.orderLines().stream()
                .map(OrderLine::lineTotal)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public Map<OrderStatus,List<Order>> groupStatus(List<Order> orders){
        return orders.stream()
                .collect(Collectors.groupingBy(Order::status));
    }

    public Optional<Order> findById(Long Id){
        return orders.stream()
                .filter(order -> order.orderId().equals(Id))
                .findFirst();
    }
}
