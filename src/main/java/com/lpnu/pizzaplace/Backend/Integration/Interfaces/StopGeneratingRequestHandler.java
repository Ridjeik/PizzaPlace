package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.StopGeneratingRequest;

public interface StopGeneratingRequestHandler {
    void handle(StopGeneratingRequest request);
}
