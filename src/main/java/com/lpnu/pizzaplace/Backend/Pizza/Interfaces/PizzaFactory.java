package com.lpnu.pizzaplace.Backend.Pizza.Interfaces;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.Pizza;

public interface PizzaFactory {
    Pizza createPizza(Customer customer, String pizzaName);
}
