package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Implementation.ReadyState;

import java.util.EnumSet;

public class Cook {

    private PizzaCreationContext currentContext;
    private boolean isStopped = false;
    private EnumSet<PizzaStateEnum> availableStates;

    public Cook(EnumSet<PizzaStateEnum> availableStates) {
        this.availableStates = availableStates;
    }


    public void processPizza(PizzaCreationContext context)
    {
        this.currentContext = context;
        new Thread(this::processPizzaImpl).start();
    }

    public PizzaCreationContext getCurrentContext() {
        return currentContext;
    }

    private void processPizzaImpl()
    {
        this.currentContext.setBeingCooked(true);
        while(this.canProcess(this.currentContext) && !this.isStopped)
        {
            this.currentContext.getPizzaState().doStep();
        }
        this.currentContext.setBeingCooked(false);
        this.currentContext = null;
    }

    public void stop(){
        isStopped = true;
    }

    public void resume(){
        isStopped = false;
    }

    public boolean canProcess(PizzaCreationContext context) {
        return this.availableStates.contains(context.getPizzaState().asEnum());
    }


    public boolean isFree()
    {
        return this.currentContext == null;
    }
}
