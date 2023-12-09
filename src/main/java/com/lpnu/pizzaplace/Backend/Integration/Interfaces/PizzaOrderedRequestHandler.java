package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaOrderedRequest;

public interface PizzaOrderedRequestHandler {
    public void handle(PizzaOrderedRequest request);
}
