package com.lpnu.pizzaplace.Backend.Integration.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.*;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.*;

public class InterMediator implements Mediator {

    private final ChangePizzaStateRequestHandler changeStateRequestHandler;

    private final NewCustomerRequestHandler newCustomerRequestHandler;

    private final PizzaReadinessRequestHandler pizzaReadinessRequestHandler;

    private final PizzaOrderedRequestHandler pizzaOrderedRequestHandler;

    private final PizzeriaInitializeRequestHandler pizzeriaInitializeRequestHandler;

    private final CookAcquiredPizzaRequestHandler cookAcquiredPizzaRequestHandler;

    public InterMediator(ChangePizzaStateRequestHandler changeStateRequestHandler,
                         NewCustomerRequestHandler newOrderRequestHandler,
                         PizzaReadinessRequestHandler pizzaReadinessRequestHandler,
                         PizzaOrderedRequestHandler pizzaOrderedRequestHandler,
                         PizzeriaInitializeRequestHandler pizzeriaInitializeRequestHandler,
                         CookAcquiredPizzaRequestHandler cookAcquiredPizzaRequestHandler) {

        this.changeStateRequestHandler = changeStateRequestHandler;
        this.newCustomerRequestHandler = newOrderRequestHandler;
        this.pizzaReadinessRequestHandler = pizzaReadinessRequestHandler;
        this.pizzaOrderedRequestHandler = pizzaOrderedRequestHandler;
        this.pizzeriaInitializeRequestHandler = pizzeriaInitializeRequestHandler;
        this.cookAcquiredPizzaRequestHandler = cookAcquiredPizzaRequestHandler;
    }

    @Override
    public void notify(Object request) {
        if (request instanceof ChangeStateRequest) {
            changeStateRequestHandler.handle((ChangeStateRequest) request);
        } else if (request instanceof NewCustomerRequest) {
            newCustomerRequestHandler.handle((NewCustomerRequest) request);
        } else if (request instanceof PizzaReadinessRequest) {
            pizzaReadinessRequestHandler.handle((PizzaReadinessRequest) request);
        } else if (request instanceof PizzaOrderedRequest) {
            pizzaOrderedRequestHandler.handle((PizzaOrderedRequest) request);
        } else if (request instanceof PizzeriaInitializeRequest) {
            pizzeriaInitializeRequestHandler.handle((PizzeriaInitializeRequest) request);
        } else if (request instanceof CookAcquiredPizzaRequest) {
            cookAcquiredPizzaRequestHandler.handle((CookAcquiredPizzaRequest) request);
        }
    }
}
