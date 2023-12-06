package com.lpnu.pizzaplace.Backend.Logging.Implementation;

import com.lpnu.pizzaplace.Backend.Logging.Contracts.LogLevel;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;

public class ConsoleLogger implements Logger {
    @Override
    public void log(LogLevel level, String message) {
        var str = '[' + level.toString() + "] " + message + '\n';
        System.out.println(str);
    }
}
