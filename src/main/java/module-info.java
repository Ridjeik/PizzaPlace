module com.lpnu.pizzaplace {
    requires java.desktop;
    requires com.formdev.flatlaf;
    requires javafx.graphics;
    requires com.google.gson;

    opens com.lpnu.pizzaplace.Backend.Configuration.Contracts to com.google.gson;
    exports com.lpnu.pizzaplace.Backend.Configuration.Contracts;
}