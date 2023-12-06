package com.lpnu.pizzaplace.Backend.Logging.Interfaces;

import com.lpnu.pizzaplace.Backend.Logging.Contracts.LogLevel;

public interface Logger {

    void log(LogLevel level, String message);

    default void trace(String message)
    {
        this.log(LogLevel.Trace, message);
    }

    default void info(String message)
    {
        this.log(LogLevel.Info, message);
    }

    default void warning(String message)
    {
        this.log(LogLevel.Warning, message);
    }

    default void error(String message)
    {
        this.log(LogLevel.Error, message);
    }

    default void critical(String message)
    {
        this.log(LogLevel.Critical, message);
    }
}
