package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.Pizzeria.Cook;

public class CookAcquiredPizzaRequest {
    private final Cook cook;

    public CookAcquiredPizzaRequest(Cook cook) {
        this.cook = cook;
    }

    public Cook getCook() {
        return cook;
    }
}
