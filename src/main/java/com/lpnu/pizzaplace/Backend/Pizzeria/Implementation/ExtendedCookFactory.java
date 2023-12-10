package com.lpnu.pizzaplace.Backend.Pizzeria.Implementation;

import com.lpnu.pizzaplace.Backend.Pizzeria.Cook;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

import java.util.EnumSet;
import java.util.Random;

public class ExtendedCookFactory implements com.lpnu.pizzaplace.Backend.Pizzeria.Interfaces.CookFactory {

    @Override
    public Cook createCook(PizzaStateEnum pizzaStateEnum) {
        return new Cook(EnumSet.of(pizzaStateEnum, PizzaStateEnum.Ordered, PizzaStateEnum.Ready), getRandomName());
    }

    public Cook createUniversalCook(){
        return new Cook(EnumSet.allOf(PizzaStateEnum.class), getRandomName());
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
}

