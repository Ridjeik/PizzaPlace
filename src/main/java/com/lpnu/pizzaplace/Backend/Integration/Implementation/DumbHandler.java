package com.lpnu.pizzaplace.Backend.Integration.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Interfaces.ChangePizzaStateRequestHandler;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;

public class DumbHandler implements ChangePizzaStateRequestHandler {

    private final Logger logger;

    public DumbHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handle(ChangeStateRequest changeStateRequest) {
        logger.trace(String.format("Pizza for %s with name %s is %s",
                changeStateRequest.getPizza().getCustomer().getName(),
                changeStateRequest.getPizza().getName(),
                changeStateRequest.getPizzaState().toString()));
    }
}
