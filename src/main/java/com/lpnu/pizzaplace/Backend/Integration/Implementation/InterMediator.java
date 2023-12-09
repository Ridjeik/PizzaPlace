package com.lpnu.pizzaplace.Backend.Integration.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.NewCustomerRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaReadinessRequest;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.ChangePizzaStateRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.NewCustomerRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.PizzaReadinessRequestHandler;

public class InterMediator implements Mediator {

    private final ChangePizzaStateRequestHandler changeStateRequestHandler;

    private final NewCustomerRequestHandler newOrderRequestHandler;

    private final PizzaReadinessRequestHandler pizzaReadinessRequestHandler;

    public InterMediator(ChangePizzaStateRequestHandler changeStateRequestHandler, NewCustomerRequestHandler newOrderRequestHandler, PizzaReadinessRequestHandler pizzaReadinessRequestHandler) {
        this.changeStateRequestHandler = changeStateRequestHandler;
        this.newOrderRequestHandler = newOrderRequestHandler;
        this.pizzaReadinessRequestHandler = pizzaReadinessRequestHandler;
    }

    @Override
    public void notify(Object request) {
        switch (request) {
            case ChangeStateRequest changeStateRequest -> changeStateRequestHandler.handle(changeStateRequest);
            case NewCustomerRequest newOrderRequest -> newOrderRequestHandler.handle(newOrderRequest);
            case PizzaReadinessRequest pizzaReadinessRequest -> pizzaReadinessRequestHandler.handle(pizzaReadinessRequest);
            case null, default -> {
            }
        }
    }
}
