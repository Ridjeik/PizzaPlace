package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaReadinessRequest;

public interface PizzaReadinessRequestHandler {
    void handle(PizzaReadinessRequest request);
}
