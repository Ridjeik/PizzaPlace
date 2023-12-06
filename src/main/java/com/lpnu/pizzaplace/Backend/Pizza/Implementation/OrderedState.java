package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class OrderedState implements PizzaState {

    private final PizzaCreationContext context;

    public OrderedState(PizzaCreationContext context) {
        this.context = context;
    }

    @Override
    public void doStep() {
        this.context.setPizzaState(new CookingState(this.context));
        this.context.getMediator().notify(new ChangeStateRequest(this.context.getPizza(), this.context.getPizzaState().asEnum()));
    }

    @Override
    public PizzaStateEnum asEnum() {
        return PizzaStateEnum.Ordered;
    }
}
