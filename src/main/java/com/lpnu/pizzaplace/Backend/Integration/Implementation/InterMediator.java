package com.lpnu.pizzaplace.Backend.Integration.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Interfaces.ChangePizzaStateRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;

public class InterMediator implements Mediator {

    private final ChangePizzaStateRequestHandler changeStateRequestHandler;

    public InterMediator(ChangePizzaStateRequestHandler changeStateRequestHandler) {
        this.changeStateRequestHandler = changeStateRequestHandler;
    }

    @Override
    public void notify(Object request) {
        if (request instanceof ChangeStateRequest)
        {
            changeStateRequestHandler.handle((ChangeStateRequest) request);
        }
    }
}
