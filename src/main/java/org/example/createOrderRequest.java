package org.example;

import java.util.List;

public record createOrderRequest(Customer customer, List<OrderLine> orderLines) {
}

