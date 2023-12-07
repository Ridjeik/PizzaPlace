package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.NewOrderRequest;

public interface ChangePizzaStateRequestHandler {
    void handle(ChangeStateRequest request);
}
