package org.example;

public sealed interface OrderStatus  permits Pending, Completed, Cancelled{
}
