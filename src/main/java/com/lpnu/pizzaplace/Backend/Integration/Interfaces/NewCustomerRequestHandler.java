package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.NewCustomerRequest;

public interface NewCustomerRequestHandler {
    void handle(NewCustomerRequest request);
}
