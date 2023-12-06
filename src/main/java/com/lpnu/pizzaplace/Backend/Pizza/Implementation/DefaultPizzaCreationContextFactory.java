package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaCreationContextFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaFactory;

import java.util.List;

public class DefaultPizzaCreationContextFactory implements PizzaCreationContextFactory
{
    private final PizzaFactory factory;

    private final Mediator mediator;

    public DefaultPizzaCreationContextFactory(PizzaFactory factory, Mediator mediator) {
        this.factory = factory;
        this.mediator = mediator;
    }

    @Override
    public List<PizzaCreationContext> createPizzaContexts(Order order) {
        return order.getPizzaNames()
                .stream()
                .map((String pizzaName) -> factory.createPizza(order.getCustomer(), pizzaName))
                .map(pizza -> new PizzaCreationContext(pizza, mediator))
                .toList();
    }
}
