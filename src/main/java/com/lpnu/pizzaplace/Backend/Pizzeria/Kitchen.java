package com.lpnu.pizzaplace.Backend.Pizzeria;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.CookAcquiredPizzaRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaOrderedRequest;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Pizzeria.Interfaces.CookFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaCreationContextFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Kitchen
{
    private final List<Cook> cooks;

    private final PizzaCreationContextFactory pizzaCreationContextFactory;

    private final Mediator mediator;

    private Logger logger;

    private List<PizzaCreationContext> waitingPizzas = Collections.synchronizedList(new LinkedList<>());

    private List<PizzaCreationContext> readyPizzas = new LinkedList<>();

    public Kitchen(ConfigSupplier configSupplier, PizzaCreationContextFactory pizzaFactory, CookFactory cookFactory, Mediator mediator, Logger logger) {
        this.mediator = mediator;
        this.logger = logger;

        if(configSupplier.getConfig().isCookDoingAllOperations()){
            this.cooks = IntStream
                    .range(0, configSupplier.getConfig().getCooksCount())
                    .mapToObj(i -> cookFactory.createUniversalCook())
                    .toList();
        }
        else{
            Stream<Cook> doughStream = IntStream.range(0, configSupplier.getConfig().getMakingDoughCooksCount())
                    .mapToObj(i -> cookFactory.createCook(PizzaStateEnum.MakingDough));

            Stream<Cook> bakingStream = IntStream.range(0, configSupplier.getConfig().getBakingCooksCount())
                    .mapToObj(i -> cookFactory.createCook(PizzaStateEnum.Cooking));

            Stream<Cook> toppingStream = IntStream.range(0, configSupplier.getConfig().getAddingToppingCooksCount())
                    .mapToObj(i -> cookFactory.createCook(PizzaStateEnum.AddingTopping));

            Stream<Cook> concatenated = Stream.concat(doughStream, Stream.concat(bakingStream, toppingStream));

            this.cooks = concatenated.collect(Collectors.toList());

        }

        this.pizzaCreationContextFactory = pizzaFactory;
        new Thread(this::reviewPizzas).start();
    }

    public void processOrder(Order order)
    {
        synchronized (waitingPizzas) {
            logger.info("Нове замовлення від " + order.getCustomer().getName() + ": " + String.join(", ", order.getPizzaNames()));
            var newContexts = pizzaCreationContextFactory.createPizzaContexts(order);
            newContexts.forEach(context ->  {
                this.mediator.notify(new PizzaOrderedRequest(context));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                waitingPizzas.add(context);
            });
        }
    }

    private void reviewPizzas()
    {
        while(true)
        {
            synchronized (waitingPizzas) {
                Iterator<PizzaCreationContext> iterator = waitingPizzas.iterator();
                while (iterator.hasNext()) {
                    PizzaCreationContext waitingPizza = iterator.next();
                    if (waitingPizza.isReady()) {
                        logger.info(waitingPizza.getPizza().getName() + " готова.");
                        iterator.remove();
                        readyPizzas.add(waitingPizza);
                    } else {
                        for (Cook cook : cooks) {
                            if (!waitingPizza.isBeingCooked() && cook.isFree() && cook.canProcess(waitingPizza)) {
                                cook.processPizza(waitingPizza);
                                logger.info("Кухар " + cook.getName() + " готує піцу " + waitingPizza.getPizza().getName() + " для " + waitingPizza.getPizza().getCustomer().getName());
                                this.mediator.notify(new CookAcquiredPizzaRequest(cook));
                                break;
                            }
                        }
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public List<Cook> getCooks() {
        return List.copyOf(cooks);
    }
}
