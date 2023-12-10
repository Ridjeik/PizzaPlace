package com.lpnu.pizzaplace.Backend.PIzzeria.Implementation;

import com.lpnu.pizzaplace.Backend.PIzzeria.Cook;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

import java.util.EnumSet;

public class CookFactory implements com.lpnu.pizzaplace.Backend.PIzzeria.Interfaces.CookFactory {
    @Override
    public Cook createCook(PizzaStateEnum pizzaStateEnum) {
        return new Cook(EnumSet.of(pizzaStateEnum, PizzaStateEnum.Ordered, PizzaStateEnum.Ready));
    }

    public Cook createUniversalCook(){
        return new Cook(EnumSet.allOf(PizzaStateEnum.class));
    }
}

