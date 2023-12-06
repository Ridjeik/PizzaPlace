package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.Pizza;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

import java.util.Objects;

public final class ChangeStateRequest {
    private final Pizza pizza;
    private final PizzaStateEnum pizzaState;

    public ChangeStateRequest(Pizza pizza, PizzaStateEnum pizzaState) {
        this.pizza = pizza;
        this.pizzaState = pizzaState;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public PizzaStateEnum getPizzaState() {
        return pizzaState;
    }

    @Override
    public String toString() {
        return "ChangeStateRequest[" +
                "pizza=" + pizza + ", " +
                "pizzaState=" + pizzaState + ']';
    }
}
