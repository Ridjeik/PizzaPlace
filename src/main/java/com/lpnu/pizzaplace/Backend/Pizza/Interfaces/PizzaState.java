package com.lpnu.pizzaplace.Backend.Pizza.Interfaces;

import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

public interface PizzaState {
    void doStep();

    PizzaStateEnum asEnum();
}
