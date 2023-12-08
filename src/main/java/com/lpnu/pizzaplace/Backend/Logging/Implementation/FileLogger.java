package com.lpnu.pizzaplace.Backend.Logging.Implementation;

import com.lpnu.pizzaplace.Backend.Logging.Contracts.LogLevel;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {

    private final String filePath = "src\\main\\resources\\PizzaPlaceLog.log";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    @Override
    public void log(LogLevel level, String message) {

        LocalDateTime timestamp = LocalDateTime.now();
        String formattedTimestamp = timestamp.format(formatter);

        var str = '[' + level.toString() + "] " + '[' + formattedTimestamp + "] " + message;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
