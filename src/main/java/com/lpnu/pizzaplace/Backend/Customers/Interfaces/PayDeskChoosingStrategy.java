package com.lpnu.pizzaplace.Backend.Customers.Interfaces;

import com.lpnu.pizzaplace.Backend.Pizzeria.PayDesk;
import com.lpnu.pizzaplace.Backend.Pizzeria.PayDeskCollection;

public interface PayDeskChoosingStrategy {
    PayDesk choosePayDesk(PayDeskCollection collection);
}
