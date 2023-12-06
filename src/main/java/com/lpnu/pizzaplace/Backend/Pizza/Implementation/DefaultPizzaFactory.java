package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.Pizza;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaFactory;

public class DefaultPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza(Customer customer, String pizzaName) {
        return new Pizza(customer, pizzaName);
    }
}
