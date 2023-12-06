package com.lpnu.pizzaplace.Backend.Orders.Interfaces;

import com.lpnu.pizzaplace.Backend.Orders.Contracts.Order;

public interface OrderFactory {
    Order createNewOrder();
}
