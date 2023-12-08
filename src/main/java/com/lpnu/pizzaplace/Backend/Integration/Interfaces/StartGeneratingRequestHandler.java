package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.StartGeneratingRequest;

public interface StartGeneratingRequestHandler {
    void handle(StartGeneratingRequest request);
}
