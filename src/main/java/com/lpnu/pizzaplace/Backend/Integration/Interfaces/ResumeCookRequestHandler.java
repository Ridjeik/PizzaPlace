package com.lpnu.pizzaplace.Backend.Integration.Interfaces;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ResumeCookRequest;

public interface ResumeCookRequestHandler {
    void handle(ResumeCookRequest request);
}
