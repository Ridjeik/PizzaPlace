package com.lpnu.pizzaplace.Backend.Pizza.Interfaces;

import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;

import java.util.List;

public interface PizzaCreationContextFactory {
    List<PizzaCreationContext> createPizzaContexts(Order order);
}
