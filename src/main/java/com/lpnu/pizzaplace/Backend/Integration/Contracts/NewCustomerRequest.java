package com.lpnu.pizzaplace.Backend.Integration.Contracts;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;
import com.lpnu.pizzaplace.Backend.Pizzeria.PayDesk;

public class NewCustomerRequest {
    private final Customer customer;

    private final PayDesk payDesk;

    public NewCustomerRequest(Customer order, PayDesk payDesk) {
        this.customer = order;
        this.payDesk = payDesk;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PayDesk getPayDesk() {
        return payDesk;
    }
}
