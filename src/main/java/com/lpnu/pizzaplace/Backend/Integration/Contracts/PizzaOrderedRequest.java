package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;

public class PizzaOrderedRequest {
    private final PizzaCreationContext context;

    public PizzaOrderedRequest(PizzaCreationContext context) {
        this.context = context;
    }

    public PizzaCreationContext getContext() {
        return context;
    }
}
