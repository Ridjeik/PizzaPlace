package com.lpnu.pizzaplace.Backend.Customers.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Interfaces.PayDeskChoosingStrategy;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDesk;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDeskCollection;

import java.util.Comparator;

public class ShortestPayDeskChoosingStrategy implements PayDeskChoosingStrategy {
    @Override
    public PayDesk choosePayDesk(PayDeskCollection collection) {
        return collection.getPayDesks().stream().min(Comparator.comparingInt(PayDesk::getCustomersCount)).orElse(null);
    }
}
