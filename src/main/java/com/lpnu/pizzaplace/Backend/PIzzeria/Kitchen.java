package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaCreationContextFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public final class Kitchen
{
    private final List<Cook> cooks;
    private final PizzaCreationContextFactory pizzaCreationContextFactory;

    private List<PizzaCreationContext> waitingPizzas = new LinkedList<>();

    private List<PizzaCreationContext> readyPizzas = new LinkedList<>();

    public Kitchen(ConfigSupplier configSupplier, PizzaCreationContextFactory factory) {
        this.cooks = IntStream
                .range(0, configSupplier.getConfig().getCooksCount())
                .mapToObj(i -> new Cook())
                .toList();
        this.pizzaCreationContextFactory = factory;
        new Thread(this::reviewPizzas).start();
    }

    public void processOrder(Order order)
    {
        var newContexts = pizzaCreationContextFactory.createPizzaContexts(order);
        waitingPizzas.addAll(newContexts);
    }

    private void reviewPizzas()
    {
        while(true)
        {
            for (PizzaCreationContext waitingPizza : waitingPizzas) {
                if (waitingPizza.isReady())
                {
                    waitingPizzas.remove(waitingPizza);
                    readyPizzas.add(waitingPizza);
                    continue;
                }
                for (Cook cook : cooks) {
                    if (!waitingPizza.isBeingCooked() && cook.isFree() && cook.canProcess(waitingPizza))
                    {
                        cook.processPizza(waitingPizza);
                        break;
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
        }
    }

}
