package com.lpnu.pizzaplace.Backend.PIzzeria.Interfaces;

import com.lpnu.pizzaplace.Backend.PIzzeria.Cook;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

public interface CookFactory {
    Cook createCook(PizzaStateEnum pizzaStateEnum);
    Cook createUniversalCook();
}
