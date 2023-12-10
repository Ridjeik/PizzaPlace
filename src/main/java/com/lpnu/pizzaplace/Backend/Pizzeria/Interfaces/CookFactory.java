package com.lpnu.pizzaplace.Backend.Pizzeria.Interfaces;

import com.lpnu.pizzaplace.Backend.Pizzeria.Cook;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

public interface CookFactory {
    Cook createCook(PizzaStateEnum pizzaStateEnum);
    Cook createUniversalCook();
}
