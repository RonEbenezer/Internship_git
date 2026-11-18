package org.example;

import java.util.List;

public record CreateOrderRequest(Customer customer, List<OrderLine> orderLines) {
}

