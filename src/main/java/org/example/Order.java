package org.example;

import java.util.List;

public record Order(Long orderId,Customer customer, List<OrderLine> orderLines, OrderStatus status) {
}
