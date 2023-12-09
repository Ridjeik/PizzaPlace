package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzeriaInitializeRequest;

public interface PizzeriaInitializeRequestHandler {
    public void handle(PizzeriaInitializeRequest request);
}
