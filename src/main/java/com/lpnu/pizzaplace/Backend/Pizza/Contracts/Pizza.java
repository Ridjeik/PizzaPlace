package com.lpnu.pizzaplace.Backend.Pizza.Contracts;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;

public class Pizza
{
    private final Customer customer;
    private final String name;

    public Pizza(Customer customer, String pizzaName)
    {
        this.customer = customer;
        this.name = pizzaName;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }
}
