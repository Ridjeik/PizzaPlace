package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.NewOrderRequest;

public interface NewOrderRequestHandler {
    void handle(NewOrderRequest request);
}
