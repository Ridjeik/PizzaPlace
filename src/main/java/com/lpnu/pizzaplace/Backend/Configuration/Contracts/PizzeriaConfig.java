package com.lpnu.pizzaplace.Backend.Configuration.Contracts;

public class PizzeriaConfig {
    private int cooksCount;

    private int payDesksCount;

    private int pizzaTypesCount;

    private int orderGenerationInterval;

    private PizzeriaConfig(int cooksCount, int payDesksCount, int pizzaTypesCount, int orderGenerationInterval) {
        this.cooksCount = cooksCount;
        this.payDesksCount = payDesksCount;
        this.pizzaTypesCount = pizzaTypesCount;
        this.orderGenerationInterval = orderGenerationInterval;
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
        private int cooksCount;
        private int payDesksCount;
        private int pizzaTypesCount;
        private int orderGenerationInterval;

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

        public PizzeriaConfig createPizzeriaConfig() {
            return new PizzeriaConfig(cooksCount, payDesksCount, pizzaTypesCount, orderGenerationInterval);
        }
    }
}
