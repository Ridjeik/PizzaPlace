package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.Pizza;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaMenu;

public class DefaultPizzaFactory implements PizzaFactory {

    private final PizzaMenu pizzaMenu;

    public DefaultPizzaFactory(PizzaMenu pizzaMenu) {
        this.pizzaMenu = pizzaMenu;
    }

    @Override
    public Pizza createPizza(Customer customer, String pizzaName) {
        return new Pizza(customer, pizzaName, pizzaMenu.getPizzaCookingDuration(pizzaName));
    }
}
