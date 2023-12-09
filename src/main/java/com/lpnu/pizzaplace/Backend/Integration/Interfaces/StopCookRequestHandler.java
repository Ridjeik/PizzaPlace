package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.StopCookRequest;

public interface StopCookRequestHandler {
    void handle(StopCookRequest request);
}
