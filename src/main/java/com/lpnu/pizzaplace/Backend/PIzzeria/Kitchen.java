package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.CookAcquiredPizzaRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.PizzaOrderedRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.ResumeCookRequest;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.StopCookRequest;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.ResumeCookRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.StopCookRequestHandler;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.PIzzeria.Interfaces.CookFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaCreationContextFactory;

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

    private List<PizzaCreationContext> waitingPizzas = new LinkedList<>();

    private List<PizzaCreationContext> readyPizzas = new LinkedList<>();
    public Kitchen(ConfigSupplier configSupplier, PizzaCreationContextFactory pizzaFactory, CookFactory cookFactory, Mediator mediator) {
        this.mediator = mediator;
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

        this.mediator = mediator;
        this.pizzaCreationContextFactory = pizzaFactory;
        new Thread(this::reviewPizzas).start();
    }

    public void processOrder(Order order)
    {
        var newContexts = pizzaCreationContextFactory.createPizzaContexts(order);
        newContexts.forEach(context -> this.mediator.notify(new PizzaOrderedRequest(context)));
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
                        this.mediator.notify(new CookAcquiredPizzaRequest(cook));
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

    public List<Cook> getCooks() {
        return List.copyOf(cooks);
    }
}
