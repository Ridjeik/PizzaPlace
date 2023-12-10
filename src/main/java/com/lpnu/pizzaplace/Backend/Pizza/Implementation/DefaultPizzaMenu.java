package com.lpnu.pizzaplace.Backend.Pizza.Implementation;

import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Pizza.Interfaces.PizzaMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DefaultPizzaMenu implements PizzaMenu {

    private final ConfigSupplier configSupplier;
    Map<String, Integer> pizzaCookingDurations = new HashMap<>();

    public DefaultPizzaMenu(ConfigSupplier configSupplier)
    {
        this.configSupplier = configSupplier;
        Random rand = new Random();
        var minimalTime = this.configSupplier.getConfig().getMinimalTimeToCookPizza();
        
        pizzaCookingDurations.put("Маргарита", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Пепероні", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Гавайська", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Кальцоне", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Капрічоза", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Діабло", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Карбонара", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Фруті ді маре", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Фунгі", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Наполетана", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Пармезана", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Біанка", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Рустіка", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Сіциліана", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Тоскана", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Тревізо", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Фіорентіна", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Чотири сезони", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Чотири сири", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Неаполь", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Турин", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Болонья", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Флоренція", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Генуя", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Піза", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Верона", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Палермо", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Барі", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Катанія", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Падуя", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Трієст", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Ліворно", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Парма", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Модена", rand.nextInt(20000) + minimalTime);
        pizzaCookingDurations.put("Перуджа", rand.nextInt(20000) + minimalTime);
    }

    @Override
    public int getPizzaCookingDuration(String pizzaName) {
        return pizzaCookingDurations.get(pizzaName);
    }

    @Override
    public List<String> getPizzaNames() {
        return pizzaCookingDurations
                .keySet()
                .stream()
                .limit(this.configSupplier.getConfig().getPizzaTypesCount())
                .toList();
    }
}
