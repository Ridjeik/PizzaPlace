package com.lpnu.pizzaplace.Backend.Configuration.Implementation;

import com.lpnu.pizzaplace.Backend.Configuration.Contracts.PizzeriaConfig;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigFactory;

public class BaseConfigFactory implements ConfigFactory {
    @Override
    public PizzeriaConfig createConfig() {
        return PizzeriaConfig
                .createBuilder()
                .setCooksCount(6)
                .setPayDesksCount(1)
                .setCookDoingAllOperations(true)
                .setMinimalTimeToCookPizza(2000)
                .setPizzaTypesCount(1)
                .setOrderGenerationInterval(5000)
                .createPizzeriaConfig();
    }
}
