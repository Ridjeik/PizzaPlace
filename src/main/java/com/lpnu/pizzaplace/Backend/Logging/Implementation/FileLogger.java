package com.lpnu.pizzaplace.Backend.Logging.Implementation;

import com.lpnu.pizzaplace.Backend.Logging.Contracts.LogLevel;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger {

    private final String filePath = "src\\main\\resources\\PizzaPlaceLog.log";
    @Override
    public void log(LogLevel level, String message) {
        var str = '[' + level.toString() + "] " + message + '\n';

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
