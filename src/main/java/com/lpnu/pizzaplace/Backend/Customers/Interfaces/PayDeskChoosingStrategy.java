package com.lpnu.pizzaplace.Backend.Customers.Interfaces;

import com.lpnu.pizzaplace.Backend.PIzzeria.PayDesk;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDeskCollection;

public interface PayDeskChoosingStrategy {
    PayDesk choosePayDesk(PayDeskCollection collection);
}
