package com.lpnu.pizzaplace.Backend.Application;

import com.lpnu.pizzaplace.Backend.Configuration.Implementation.BaseConfigFactory;
import com.lpnu.pizzaplace.Backend.Configuration.Implementation.StaticConfigSupplier;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigFactory;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Customers.Implementation.DefaultCustomerFactory;
import com.lpnu.pizzaplace.Backend.Customers.Implementation.DefaultPayDeskChoosingStrategyFactory;
import com.lpnu.pizzaplace.Backend.Customers.Interfaces.CustomerFactory;
import com.lpnu.pizzaplace.Backend.Customers.Interfaces.PayDeskChoosingStrategyFactory;
import com.lpnu.pizzaplace.Backend.Integration.Implementation.DumbHandler;
import com.lpnu.pizzaplace.Backend.Integration.Implementation.InterMediator;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.ChangePizzaStateRequestHandler;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Logging.Implementation.ConsoleLogger;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;
import com.lpnu.pizzaplace.Backend.Orders.Implementation.EquallyTimedOrderSupplier;
import com.lpnu.pizzaplace.Backend.Orders.Implementation.OneOrderSupplier;
import com.lpnu.pizzaplace.Backend.Orders.Implementation.TestOrderFactory;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderFactory;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderGenerationObserver;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderSupplier;
import com.lpnu.pizzaplace.Backend.PIzzeria.Kitchen;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDeskCollection;
import com.lpnu.pizzaplace.Backend.PIzzeria.Pizzeria;
import com.lpnu.pizzaplace.Backend.Pizza.Implementation.DefaultPizzaCreationContextFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Implementation.DefaultPizzaFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaCreationContextFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaFactory;
import com.lpnu.pizzaplace.DI.ServiceCollection;
import com.lpnu.pizzaplace.GUI.ConfigForm;

public class Main {
    public static void main(String[] args) {
        ServiceCollection collection = new ServiceCollection();
        collection.registerSingleton(ConfigFactory.class, ConfigForm.class); // Was BaseConfigFactory
        collection.registerSingleton(ConfigSupplier.class, StaticConfigSupplier.class);
        collection.registerSingleton(PizzaCreationContextFactory.class, DefaultPizzaCreationContextFactory.class);
        collection.registerSingleton(PizzaFactory.class, DefaultPizzaFactory.class);
        collection.registerSingleton(Kitchen.class);
        collection.registerSingleton(PayDeskCollection.class);
        collection.registerSingleton(OrderGenerationObserver.class, Pizzeria.class);
        collection.registerSingleton(OrderSupplier.class, OneOrderSupplier.class);
        collection.registerSingleton(OrderFactory.class, TestOrderFactory.class);
        collection.registerSingleton(Logger.class, ConsoleLogger.class);
        collection.registerSingleton(Mediator.class, InterMediator.class);
        collection.registerSingleton(ChangePizzaStateRequestHandler.class, DumbHandler.class);
        collection.registerSingleton(CustomerFactory.class, DefaultCustomerFactory.class);
        collection.registerSingleton(PayDeskChoosingStrategyFactory.class, DefaultPayDeskChoosingStrategyFactory.class);

        var container = collection.buildContainer();
        container.getService(OrderSupplier.class).startSupplying();
    }
}
