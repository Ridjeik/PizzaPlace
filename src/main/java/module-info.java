module com.lpnu.pizzaplace {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lpnu.pizzaplace.GUI to javafx.fxml;
    exports com.lpnu.pizzaplace.GUI;
}