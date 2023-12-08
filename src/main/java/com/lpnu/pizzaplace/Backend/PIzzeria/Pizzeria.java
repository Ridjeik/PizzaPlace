package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.NewOrderRequest;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.Mediator;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderGenerationObserver;

public class Pizzeria implements OrderGenerationObserver
{
     private final Kitchen kitchen;
     private final PayDeskCollection payDeskCollection;
     private final ConfigSupplier configSupplier;

     private final Mediator mediator;

     public Pizzeria(Kitchen kitchen, PayDeskCollection payDeskCollection, ConfigSupplier configSupplier, Mediator mediator) {
          this.kitchen = kitchen;
          this.payDeskCollection = payDeskCollection;
          this.configSupplier = configSupplier;
          this.mediator = mediator;
     }

     @Override
     public void processOrder(Order order) {
          this.mediator.notify(new NewOrderRequest(order));
          kitchen.processOrder(order);
     }
}
