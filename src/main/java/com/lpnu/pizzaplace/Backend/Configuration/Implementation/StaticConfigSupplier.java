package com.lpnu.pizzaplace.Backend.Configuration.Implementation;

import com.lpnu.pizzaplace.Backend.Configuration.Contracts.PizzeriaConfig;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigFactory;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;

public class StaticConfigSupplier implements ConfigSupplier {

    private final ConfigFactory configFactory;

    private PizzeriaConfig cachedConfig;

    public StaticConfigSupplier(ConfigFactory configFactory) {
        this.configFactory = configFactory;
    }

    @Override
    public PizzeriaConfig getConfig() {
        if (cachedConfig == null)
        {
            cachedConfig = configFactory.createConfig();
        }
        return cachedConfig;
    }
}
