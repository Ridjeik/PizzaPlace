package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.PIzzeria.Cook;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDesk;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDeskCollection;

import java.util.List;

public class PizzeriaInitializeRequest {
    private final PayDeskCollection payDesks;

    private final List<Cook> cooks;

    public PizzeriaInitializeRequest(PayDeskCollection payDesks, List<Cook> cooks) {
        this.payDesks = payDesks;
        this.cooks = cooks;
    }

    public PayDeskCollection getPayDesks() {
        return payDesks;
    }

    public List<Cook> getCooks() {
        return cooks;
    }
}
