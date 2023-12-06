package com.lpnu.pizzaplace.Backend.PIzzeria;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class PayDesk {
    Queue<Customer> customers = new LinkedList<>();

    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }

    public int getCustomersCount()
    {
        return customers.size();
    }

    public void deleteCustomer(Customer customer)
    {
        customers.remove(customer);
    }
}
