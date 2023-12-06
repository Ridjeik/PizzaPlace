package com.lpnu.pizzaplace.Backend.Orders.Implementation;

import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderFactory;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderGenerationObserver;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderSupplier;

public class OneOrderSupplier implements OrderSupplier {

    private final OrderFactory orderFactory;
    private final OrderGenerationObserver orderGenerationObserver;

    public OneOrderSupplier(OrderFactory orderFactory, OrderGenerationObserver orderGenerationObserver) {
        this.orderFactory = orderFactory;
        this.orderGenerationObserver = orderGenerationObserver;
    }

    @Override
    public void startSupplying() {
        var newOrder = orderFactory.createNewOrder();
        orderGenerationObserver.processOrder(newOrder);
    }

    @Override
    public void stopSupplying() {

    }
}
