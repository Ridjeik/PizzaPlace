package com.lpnu.pizzaplace.Backend.Pizza.Interfaces;

import java.util.List;

public interface PizzaMenu {
    public int getPizzaCookingDuration(String pizzaName);

    public List<String> getPizzaNames();
}
