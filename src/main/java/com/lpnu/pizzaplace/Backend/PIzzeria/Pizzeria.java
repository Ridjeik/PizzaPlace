package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Configuration.Contracts.PizzeriaConfig;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderGenerationObserver;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaCreationContextFactory;

import java.util.ArrayList;

public class Pizzeria implements OrderGenerationObserver
{
     private final Kitchen kitchen;
     private final PayDeskCollection payDeskCollection;
     private final ConfigSupplier configSupplier;

     public Pizzeria(Kitchen kitchen, PayDeskCollection payDeskCollection, ConfigSupplier configSupplier) {
          this.kitchen = kitchen;
          this.payDeskCollection = payDeskCollection;
          this.configSupplier = configSupplier;
     }

     @Override
     public void processOrder(Order order) {
          System.out.println(order);

          kitchen.processOrder(order);
     }
}
