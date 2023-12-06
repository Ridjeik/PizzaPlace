package com.lpnu.pizzaplace.Backend.Orders.Implementation;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderFactory;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderGenerationObserver;
import com.lpnu.pizzaplace.Backend.Orders.Interfaces.OrderSupplier;

public class EquallyTimedOrderSupplier implements OrderSupplier
{
    private final int supplyingInterval;
    private final OrderFactory orderFactory;
    private final OrderGenerationObserver orderGenerationObserver;
    private final Thread supplyThread;

    public EquallyTimedOrderSupplier(ConfigSupplier configSupplier, OrderFactory orderFactory, OrderGenerationObserver orderGenerationObserver)
    {
        this.supplyThread = new Thread(this::supply);
        this.supplyingInterval = configSupplier.getConfig().getOrderGenerationInterval();
        this.orderFactory = orderFactory;
        this.orderGenerationObserver = orderGenerationObserver;
    }

    private void supply()
    {
        while(true)
        {
            var newOrder = orderFactory.createNewOrder();
            orderGenerationObserver.processOrder(newOrder);
            try {
                Thread.sleep(supplyingInterval);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void startSupplying()
    {
        supplyThread.start();
    }

    @Override
    public void stopSupplying()
    {
        supplyThread.interrupt();
    }
}
