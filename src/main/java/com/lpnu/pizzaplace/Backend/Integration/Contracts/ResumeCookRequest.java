package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.PIzzeria.Cook;

public class ResumeCookRequest {
    private final Cook cook;

    public ResumeCookRequest(Cook cook) {
        this.cook = cook;
    }

    public Cook getCook() {
        return cook;
    }
}
