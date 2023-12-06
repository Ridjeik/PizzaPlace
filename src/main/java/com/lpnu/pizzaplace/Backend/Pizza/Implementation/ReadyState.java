package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class ReadyState implements PizzaState {

    private final PizzaCreationContext context;

    public ReadyState(PizzaCreationContext context) {
        this.context = context;
    }

    @Override
    public void doStep() {
        this.context.setReady(true);
    }

    @Override
    public PizzaStateEnum asEnum() {
        return PizzaStateEnum.Ready;
    }
}
