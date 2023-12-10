package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.CookAcquiredPizzaRequest;

public interface CookAcquiredPizzaRequestHandler {
    void handle(CookAcquiredPizzaRequest cookAcquiredPizzaRequest);
}
