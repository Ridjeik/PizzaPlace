package com.lpnu.pizzaplace.Backend.Orders.Contracts;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;

import java.util.List;

public final class Order {
    private final List<String> pizzaNames;
    private final Customer customer;

    public Order(List<String> pizzaNames, Customer customer) {
        this.pizzaNames = pizzaNames;
        this.customer = customer;
    }

    public List<String> getPizzaNames() {
        return pizzaNames;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order[" +
                "pizzaNames=" + pizzaNames + ", " +
                "customer=" + customer + ']';
    }
}
