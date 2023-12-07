package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.Pizza;

public class PizzaReadinessRequest {
    private final Pizza pizza;

    public PizzaReadinessRequest(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }
}
