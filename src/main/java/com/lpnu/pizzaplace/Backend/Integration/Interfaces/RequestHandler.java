package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

public interface RequestHandler <TRequest> {
    void handle(TRequest request);
}
