package com.lpnu.pizzaplace.Backend.Customers.Contracts;

import com.lpnu.pizzaplace.Backend.Customers.Interfaces.PayDeskChoosingStrategy;
import com.lpnu.pizzaplace.Backend.Pizzeria.PayDesk;
import com.lpnu.pizzaplace.Backend.Pizzeria.PayDeskCollection;

public class Customer {
    private final String name;

    private final PayDeskChoosingStrategy payDeskChoosingStrategy;

    public Customer(String name, PayDeskChoosingStrategy payDeskChoosingStrategy) {
        this.name = name;
        this.payDeskChoosingStrategy = payDeskChoosingStrategy;
    }

    public String getName() {
        return name;
    }

    public PayDesk choosePayDesk(PayDeskCollection collection)
    {
        return payDeskChoosingStrategy.choosePayDesk(collection);
    }
}
