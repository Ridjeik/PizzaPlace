package com.lpnu.pizzaplace.Backend.Configuration.Implementation;

import com.lpnu.pizzaplace.Backend.Configuration.Contracts.PizzeriaConfig;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigFactory;

public class BaseConfigFactory implements ConfigFactory {
    @Override
    public PizzeriaConfig createConfig() {
        return PizzeriaConfig
                .createBuilder()
                .setCooksCount(3)
                .setPayDesksCount(3)
                .setPizzaTypesCount(1)
                .setOrderGenerationInterval(2000)
                .createPizzeriaConfig();
    }
}
