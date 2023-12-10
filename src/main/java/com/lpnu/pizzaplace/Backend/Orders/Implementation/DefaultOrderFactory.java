package com.lpnu.pizzaplace.Backend.Orders.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Interfaces.CustomerFactory;
import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderFactory;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaMenu;

import java.util.LinkedList;
import java.util.Random;

public class DefaultOrderFactory implements OrderFactory {
    private final CustomerFactory factory;

    private final PizzaMenu pizzaMenu;

    public DefaultOrderFactory(CustomerFactory factory, PizzaMenu pizzaMenu) {
        this.factory = factory;
        this.pizzaMenu = pizzaMenu;
    }

    @Override
    public Order createNewOrder() {
        var rnd = new Random();
        var pizzaOrdered = rnd.nextInt(1, 6);
        var listOfAllPizzas = pizzaMenu.getPizzaNames();
        var listOfOrderedPizzas = new LinkedList<String>();
        for(int i = 0; i < pizzaOrdered; i++)
        {
            var randomPizza = listOfAllPizzas.get(rnd.nextInt(0, listOfAllPizzas.size()));
            listOfOrderedPizzas.add(randomPizza);
        }

        return new Order(listOfOrderedPizzas, factory.createCustomer());
    }
}
