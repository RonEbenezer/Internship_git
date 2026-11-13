package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


        OrderService service = new OrderService(List.of(order));

        BigDecimal actual = service.totalFor(order);

        BigDecimal expected = new BigDecimal("3200");

        assertEquals(expected, actual);
    }
    @Test
    void orderStatusVerify(){
        Customer customer1 = new Customer(
                1001L,
                "Ron",
                "ron@gmail.com"
        );

        Order order1 = new Order(1010L, customer1, List.of(),new Pending());
        Order order2 = new Order(1011L, customer1, List.of(),new Completed());
        Order order3 = new Order(1012L, customer1, List.of(),new Cancelled());
        Order order4 = new Order(1013L, customer1, List.of(),new Pending());

        List<Order> orders = List.of(order1,order2,order3,order4);

        OrderService res = new OrderService(orders);
        Map<OrderStatus, List<Order>> result = res.orderByStatus(orders);

        assertEquals(2, result.get(new Pending()).size());
        assertEquals(1, result.get(new Completed()).size());
        assertEquals(1, result.get(new Cancelled()).size());
    }
    @Test
    void testFindId() {
        Customer customer1 = new Customer(
                1001L,
                "Ron",
                "ron@gmail.com"
        );

        Order order1 = new Order(1010L, customer1, List.of(), new Pending());
        Order order2 = new Order(1011L, customer1, List.of(), new Completed());
        Order order3 = new Order(1012L, customer1, List.of(), new Cancelled());
        Order order4 = new Order(1013L, customer1, List.of(), new Pending());

        OrderService service = new OrderService(List.of(order1, order2,order3,order4));
        Optional<Order> result = service.findById(999L);

        assertTrue(result.isEmpty());
    }
    @Test
    void testFindIdFound() {

        Customer customer = new Customer(
                1001L,
                "Ron",
                "ron@gmail.com"
        );

        Order order = new Order(
                1010L,
                customer,
                List.of(),
                new Pending()
        );

        OrderService service = new OrderService(List.of(order));

        Optional<Order> result = service.findById(1010L);

        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }
}
