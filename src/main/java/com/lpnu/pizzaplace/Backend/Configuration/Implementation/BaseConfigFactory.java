package com.lpnu.pizzaplace.Backend.Configuration.Implementation;

import com.lpnu.pizzaplace.Backend.Configuration.Contracts.PizzeriaConfig;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigFactory;

public class BaseConfigFactory implements ConfigFactory {
    @Override
    public PizzeriaConfig createConfig() {
        return PizzeriaConfig
                .createBuilder()
                .setCooksCount(9)
                .setPayDesksCount(1)
                .setCookDoingAllOperations(false)
                .setBakingCooksCount(3)
                .setMakingDoughCooksCount(3)
                .setAddingToppingCooksCount(3)
                .setMinimalTimeToCookPizza(2000)
                .setPizzaTypesCount(1)
                .setOrderGenerationInterval(5000)
                .createPizzeriaConfig();

        /*.createBuilder()
                .setCooksCount(9)
                .setPayDesksCount(1)
                .setCookDoingAllOperations(false)
                .setBakingCooksCount(3)
                .setMakingDoughCooksCount(3)
                .setAddingToppingCooksCount(3)
                .setMinimalTimeToCookPizza(2000)
                .setPizzaTypesCount(1)
                .setOrderGenerationInterval(5000)
                .createPizzeriaConfig();=
        * */
    }
}
