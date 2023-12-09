package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Implementation.ReadyState;

public class Cook {

    private PizzaCreationContext currentContext;
    private boolean isStopped = false;

    public void processPizza(PizzaCreationContext context)
    {
        this.currentContext = context;
        new Thread(this::processPizzaImpl).start();
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

    public boolean canProcess(PizzaCreationContext context)
    {
        return !(context.getPizzaState() instanceof ReadyState);
    }

    public boolean isFree()
    {
        return this.currentContext == null;
    }
}
