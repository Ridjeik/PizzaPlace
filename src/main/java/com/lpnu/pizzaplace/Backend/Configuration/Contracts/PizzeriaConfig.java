package com.lpnu.pizzaplace.Backend.Configuration.Contracts;

public class PizzeriaConfig {
    private final int payDesksCount;

    private final int cooksCount;

    private final int pizzaTypesCount;

    private final int minimalTimeToCookPizza;

    private final int orderGenerationInterval;

    private final boolean isCookDoingAllOperations;

    private int makingDoughCooksCount;

    private int addingToppingCooksCount;

    private int bakingCooksCount;

    private PizzeriaConfig(int cooksCount, int payDesksCount, int pizzaTypesCount, int minimalTimeToCookPizza,
                           int orderGenerationInterval, boolean isCookDoingAllOperations) {
        this.cooksCount = cooksCount;
        this.payDesksCount = payDesksCount;
        this.pizzaTypesCount = pizzaTypesCount;
        this.minimalTimeToCookPizza = minimalTimeToCookPizza;
        this.orderGenerationInterval = orderGenerationInterval;
        this.isCookDoingAllOperations = isCookDoingAllOperations;
    }

    private PizzeriaConfig(int cooksCount, int payDesksCount, int pizzaTypesCount, int minimalTimeToCookPizza,
                           int orderGenerationInterval, boolean isCookDoingAllOperations, int makingDoughCooksCount,
                           int addingToppingCooksCount, int bakingCooksCount) {
        this.cooksCount = cooksCount;
        this.payDesksCount = payDesksCount;
        this.pizzaTypesCount = pizzaTypesCount;
        this.minimalTimeToCookPizza = minimalTimeToCookPizza;
        this.orderGenerationInterval = orderGenerationInterval;
        this.isCookDoingAllOperations = isCookDoingAllOperations;
        this.makingDoughCooksCount = makingDoughCooksCount;
        this.addingToppingCooksCount = addingToppingCooksCount;
        this.bakingCooksCount = bakingCooksCount;
    }

    public int getCooksCount() {
        return cooksCount;
    }

    public int getPayDesksCount() {
        return payDesksCount;
    }

    public int getPizzaTypesCount() {
        return pizzaTypesCount;
    }

    public int getOrderGenerationInterval() { return orderGenerationInterval; }


    public int getMinimalTimeToCookPizza() {
        return minimalTimeToCookPizza;
    }

    public boolean isCookDoingAllOperations() {
        return isCookDoingAllOperations;
    }

    @Override
    public String toString() {
        return "PizzeriaConfig{" +
                "cooksCount=" + cooksCount +
                ", payDesksCount=" + payDesksCount +
                ", pizzaTypesCount=" + pizzaTypesCount +
                '}';
    }

    public static PizzeriaConfigBuilder createBuilder()
    {
        return new PizzeriaConfigBuilder();
    }

    public static class PizzeriaConfigBuilder {
        private int payDesksCount;

        private int cooksCount;

        private int pizzaTypesCount;

        private int minimalTimeToCookPizza;

        private int orderGenerationInterval;

        private boolean isCookDoingAllOperations;

        private int makingDoughCooksCount;

        private int addingToppingCooksCount;

        private int bakingCooksCount;

        public PizzeriaConfigBuilder setCooksCount(int cooksCount) {
            this.cooksCount = cooksCount;
            return this;
        }

        public PizzeriaConfigBuilder setPayDesksCount(int payDesksCount) {
            this.payDesksCount = payDesksCount;
            return this;
        }

        public PizzeriaConfigBuilder setPizzaTypesCount(int pizzaTypesCount) {
            this.pizzaTypesCount = pizzaTypesCount;
            return this;
        }

        public PizzeriaConfigBuilder setOrderGenerationInterval(int orderGenerationInterval) {
            this.orderGenerationInterval = orderGenerationInterval;
            return this;
        }

        public PizzeriaConfigBuilder setMinimalTimeToCookPizza(int minimalTimeToCookPizza) {
            this.minimalTimeToCookPizza = minimalTimeToCookPizza;
            return this;
        }

        public PizzeriaConfigBuilder setCookDoingAllOperations(boolean cookDoingAllOperations) {
            this.isCookDoingAllOperations = cookDoingAllOperations;
            return this;
        }

        public PizzeriaConfigBuilder setMakingDoughCooksCount(int makingDoughCooksCount) {
            this.makingDoughCooksCount = makingDoughCooksCount;
            return this;
        }

        public PizzeriaConfigBuilder setAddingToppingCooksCount(int addingToppingCooksCount) {
            this.addingToppingCooksCount = addingToppingCooksCount;
            return this;
        }

        public PizzeriaConfigBuilder setBakingCooksCount(int bakingCooksCount) {
            this.bakingCooksCount = bakingCooksCount;
            return this;
        }

        public PizzeriaConfig createPizzeriaConfig() {
            if (isCookDoingAllOperations) {
                return new PizzeriaConfig(cooksCount, payDesksCount, pizzaTypesCount,
                        minimalTimeToCookPizza,orderGenerationInterval, true);
            } else {
                return new PizzeriaConfig(cooksCount, payDesksCount, pizzaTypesCount,
                        minimalTimeToCookPizza,orderGenerationInterval, false,
                        makingDoughCooksCount, addingToppingCooksCount, bakingCooksCount);
            }
        }
    }
}
