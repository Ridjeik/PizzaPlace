package com.lpnu.pizzaplace.Backend.Pizza.Contracts;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;

public class Pizza
{
    private final Customer customer;
    private final String name;
    private final int cookingTime;

    public Pizza(Customer customer, String pizzaName, int cookingTime)
    {
        this.customer = customer;
        this.name = pizzaName;
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
