package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Implementation.ReadyState;

import java.util.EnumSet;

public class Cook {

    private final String name;

    private PizzaCreationContext currentContext;

    private boolean isStopped = false;
    private final EnumSet<PizzaStateEnum> availableStates;

    public Cook(EnumSet<PizzaStateEnum> availableStates, String name) {
        this.availableStates = availableStates;
        this.name = name;
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
        return this.availableStates.contains(context.getPizzaState().asEnum()) && !isStopped;
    }


    public boolean isFree()
    {
        return this.currentContext == null;
    }

    public String getName() {
        return name;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public String getSpecialization() {
        if (this.availableStates.containsAll(EnumSet.allOf(PizzaStateEnum.class))) {
            return "Всі";
        } else {
            return this.availableStates.contains(PizzaStateEnum.MakingDough) ? "Тісто" :
                    this.availableStates.contains(PizzaStateEnum.AddingTopping) ? "Начинка" :
                    "Пекар";
        }
    }
}
