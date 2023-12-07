package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Contracts.ChangeStateRequest;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class MakingDoughState implements PizzaState {

    private final PizzaCreationContext context;
    
    // Test value
    private int makingDoughTime = 5000;

    public MakingDoughState(PizzaCreationContext context) {
        this.context = context;
    }
    @Override
    public void doStep() {
        try {
            Thread.sleep(makingDoughTime);
            context.setPizzaState(new AddingToppingState(this.context));
            this.context.getMediator().notify(new ChangeStateRequest(this.context.getPizza(), this.context.getPizzaState().asEnum()));
        } catch (InterruptedException ignored) {

        }
    }

    @Override
    public PizzaStateEnum asEnum() {
        return PizzaStateEnum.MakingDough;
    }
}
