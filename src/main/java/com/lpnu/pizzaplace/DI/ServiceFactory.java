package com.lpnu.pizzaplace.DI;

public interface ServiceFactory<TInterface> {
    TInterface getService();
}
