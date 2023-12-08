package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class AddingToppingState implements PizzaState {
    private final PizzaCreationContext context;

    // Test value
    private int addintToppingTime = 3000;

    public AddingToppingState(PizzaCreationContext context) {
        this.context = context;
    }
    @Override
    public void doStep() {
        try {
            Thread.sleep(addintToppingTime);
            context.setPizzaState(new CookingState(this.context));
            this.context.getMediator().notify(new ChangeStateRequest(this.context.getPizza(), this.context.getPizzaState().asEnum()));
        } catch (InterruptedException ignored) {

        }
    }

    @Override
    public PizzaStateEnum asEnum() {
        return PizzaStateEnum.AddingTopping;
    }
}
