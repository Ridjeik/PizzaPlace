package com.lpnu.pizzaplace.Backend.Orders.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Interfaces.CustomerFactory;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderFactory;

import java.util.LinkedList;

public class TestOrderFactory implements OrderFactory {

    private final CustomerFactory factory;

    public TestOrderFactory(CustomerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Order createNewOrder() {
        var pizzaNames = new LinkedList<String>();
        pizzaNames.add("Pepperoni");
        return new Order(pizzaNames, factory.createCustomer());
    }
}
