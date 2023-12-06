package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class CookingState implements PizzaState {

    private final PizzaCreationContext context;

    public CookingState(PizzaCreationContext context) {
        this.context = context;
    }
    @Override
    public void doStep() {
        try {
            Thread.sleep(10000);
            context.setPizzaState(new ReadyState(this.context));
            this.context.getMediator().notify(new ChangeStateRequest(this.context.getPizza(), this.context.getPizzaState().asEnum()));
        } catch (InterruptedException ignored) {

        }
    }

    @Override
    public PizzaStateEnum asEnum() {
        return PizzaStateEnum.Cooking;
    }
}
