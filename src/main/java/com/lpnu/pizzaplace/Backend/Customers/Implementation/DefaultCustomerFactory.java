package com.lpnu.pizzaplace.Backend.Customers.Implementation;

import com.lpnu.pizzaplace.Backend.Customers.Contracts.Customer;
import com.lpnu.pizzaplace.Backend.Customers.Interfaces.CustomerFactory;
import com.lpnu.pizzaplace.Backend.Customers.Interfaces.PayDeskChoosingStrategyFactory;

import java.util.Random;

public class DefaultCustomerFactory implements CustomerFactory
{
    private final PayDeskChoosingStrategyFactory payDeskChoosingStrategyFactory;

    public DefaultCustomerFactory(PayDeskChoosingStrategyFactory payDeskChoosingStrategyFactory) {
        this.payDeskChoosingStrategyFactory = payDeskChoosingStrategyFactory;
    }

    private static final String[] names = {
            "Олександр", "Максим", "Іван", "Артем", "Дмитро",
            "Михайло", "Нікіта", "Данило", "Андрій", "Єгор",
            "Сергій", "Роман", "Владислав", "Ілля", "Кирило",
            "Матвій", "Антон", "Олег", "Ярослав", "Богдан",
            "Віктор", "Георгій", "Степан", "Вадим", "Володимир",
            "Захар", "Тимофій", "Гліб", "Марк", "Лев",
            "Тарас", "Павло", "Юрій", "Євген", "Руслан",
            "Василь", "Олесь", "Ігор", "Марія", "Анастасія",
            "Дарія", "Анна", "Аліна", "Юлія", "Софія",
            "Вікторія", "Катерина", "Олена", "Наталія", "Ірина"
    };

    public static String getRandomName() {
        int randomIndex = new Random().nextInt(names.length);
        return names[randomIndex];
    }

    @Override
    public Customer createCustomer() {
        return new Customer(getRandomName(), payDeskChoosingStrategyFactory.createPayDeskChoosingStrategy());
    }
}
