package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;

public interface ChangePizzaStateRequestHandler {
    void handle(ChangeStateRequest request);
}
