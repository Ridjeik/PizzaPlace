package com.lpnu.pizzaplace.Backend.Pizzeria;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;

import java.util.List;
import java.util.stream.IntStream;

public class PayDeskCollection {
    private final List<PayDesk> payDesks;

    public PayDeskCollection(ConfigSupplier configSupplier) {
        this.payDesks = IntStream
                .range(0, configSupplier.getConfig().getPayDesksCount())
                .mapToObj(i -> new PayDesk())
                .toList();
    }

    public List<PayDesk> getPayDesks() {
        return List.copyOf(payDesks);
    }
}
