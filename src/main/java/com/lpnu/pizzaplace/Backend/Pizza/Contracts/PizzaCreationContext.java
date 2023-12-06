package com.lpnu.pizzaplace.Backend.Pizza.Contracts;

import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Pizza.Implementation.OrderedState;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaState;

public class PizzaCreationContext {
    private final Pizza pizza;

    private PizzaState pizzaState;

    private boolean isBeingCooked;

    private boolean isReady;

    private final Mediator mediator;

    public PizzaCreationContext(Pizza pizza, Mediator mediator) {
        this.pizza = pizza;
        this.mediator = mediator;
        this.pizzaState = new OrderedState(this);
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizzaState(PizzaState pizzaState) {
        this.pizzaState = pizzaState;
    }

    public PizzaState getPizzaState() {
        return pizzaState;
    }

    public boolean isBeingCooked() {
        return isBeingCooked;
    }

    public void setBeingCooked(boolean beingCooked) {
        isBeingCooked = beingCooked;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public Mediator getMediator() {
        return mediator;
    }
}
