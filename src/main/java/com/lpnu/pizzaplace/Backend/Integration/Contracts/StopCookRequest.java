package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.PIzzeria.Cook;

public class StopCookRequest {
    private final Cook cook;

    public StopCookRequest(Cook cook) {
        this.cook = cook;
    }

    public Cook getCook() {
        return cook;
    }
}
