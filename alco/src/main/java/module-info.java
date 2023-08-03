module michael.alco {
    requires javafx.controls;
    requires javafx.fxml;


    opens michael.alco to javafx.fxml;
    exports michael.alco;
    exports michael.alco.controllers;
    opens michael.alco.controllers to javafx.fxml;
}