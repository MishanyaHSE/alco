package michael.alco.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class YearsController {
    @FXML
    private Label errorLabel;

    @FXML
    private void previousPageClick() {
        try {
            Utility.changeScene("diseases-view.fxml", errorLabel, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextPageClick() {
        try {
            Utility.changeScene("?", errorLabel, 5, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
