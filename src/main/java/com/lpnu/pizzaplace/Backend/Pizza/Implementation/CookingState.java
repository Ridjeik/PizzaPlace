package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaReadinessRequest;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class CookingState implements PizzaState {

    private final PizzaCreationContext context;

    private static final double cookingFactor = 0.6;

    public CookingState(PizzaCreationContext context) {
        this.context = context;
    }
    @Override
    public void doStep() {
        try {
            Thread.sleep((long) (this.context.getPizza().getCookingTime() * cookingFactor));
            context.setPizzaState(new ReadyState(this.context));
            this.context.setReady(true);
            this.context.getMediator().notify(new PizzaReadinessRequest(this.context.getPizza()));
        } catch (InterruptedException ignored) {

        }
    }

    @Override
    public PizzaStateEnum asEnum() {
        return PizzaStateEnum.Cooking;
    }
}
