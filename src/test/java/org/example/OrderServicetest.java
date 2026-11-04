package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    @Test
    void shouldCalculateOrderTotal() {
        Customer customer = new Customer(
                1001L,
                "Ron",
                "ron@gmail.com"
        );

        OrderLine keyboard = new OrderLine(
                "P101",
                "Keyboard",
                2,
                new BigDecimal("1200")
        );

        OrderLine mouse = new OrderLine(
                "P102",
                "Mouse",
                1,
                new BigDecimal("800")
        );

        Order order = new Order(
                101L,
                customer,
                List.of(keyboard, mouse),
                new Pending()
        );

        Order order1 = new Order(
                102L,
                customer,
                List.of(keyboard, mouse),
                new Completed()
        );

        Order order2 = new Order(
                103L,
                customer,
                List.of(keyboard, mouse),
                new Completed()
        );

        OrderServices service = new OrderServices(List.of(order));

        BigDecimal actual = service.findTot(order);

        BigDecimal expected = new BigDecimal("3200");

        assertEquals(expected, actual);
    }

}