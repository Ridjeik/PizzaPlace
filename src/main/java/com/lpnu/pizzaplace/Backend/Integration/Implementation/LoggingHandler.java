package com.lpnu.pizzaplace.Backend.Integration.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.NewOrderRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaReadinessRequest;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.ChangePizzaStateRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.NewOrderRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.PizzaReadinessRequestHandler;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;

public class LoggingHandler implements ChangePizzaStateRequestHandler, NewOrderRequestHandler, PizzaReadinessRequestHandler {

    private final Logger logger;

    public LoggingHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handle(ChangeStateRequest request) {
        logger.trace(String.format("Pizza for %s with name %s is %s",
                request.getPizza().getCustomer().getName(),
                request.getPizza().getName(),
                request.getPizzaState().toString()));
    }

    @Override
    public void handle(NewOrderRequest request) {
        logger.trace(String.format("%s came to pizzeria", request.getOrder().getCustomer().getName()));
    }

    @Override
    public void handle(PizzaReadinessRequest request) {
        logger.trace(String.format("Pizza for %s with name %s is ready!",
                request.getPizza().getCustomer().getName(),
                request.getPizza().getName()));
    }
}
