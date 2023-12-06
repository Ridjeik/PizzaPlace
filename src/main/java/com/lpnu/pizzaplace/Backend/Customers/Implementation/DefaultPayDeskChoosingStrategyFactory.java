package com.lpnu.pizzaplace.Backend.Customers.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Interfaces.PayDeskChoosingStrategy;
import com.lpnu.pizzaplace.Backend.Customers.Interfaces.PayDeskChoosingStrategyFactory;

public class DefaultPayDeskChoosingStrategyFactory implements PayDeskChoosingStrategyFactory {
    @Override
    public PayDeskChoosingStrategy createPayDeskChoosingStrategy() {
        return new ShortestPayDeskChoosingStrategy();
    }
}
