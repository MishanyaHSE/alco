package michael.alco.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import michael.alco.model.DiseasesBase;

import java.io.IOException;

public class YearsController {

    @FXML
    private Label errorLabel;

    @FXML
    TextField inputField;

    @FXML
    private void previousPageClick() {
        try {
            Utility.changeScene("diseases-view.fxml", errorLabel, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void lowEffictiveClick() {loadNextScene(Integer.MAX_VALUE);
    }

    @FXML
    private void aboveThirtyFiveClick() {
        loadNextScene(40);
    }

    @FXML
    private void twentyFiveClick() {
        loadNextScene(25);
    }

    @FXML
    private void thirtyFiveClick() {
        loadNextScene(35);
    }

    private void loadNextScene(int percent) {
        if (isCorrectInput()) {
            DiseasesBase.numberOfYears = Integer.parseInt(inputField.getText().trim());
            DiseasesBase.percent = percent;
            try {
                Utility.changeScene("recommendation-view.fxml", errorLabel, 800, 600);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setVisible(true);
        }
    }

    private Boolean isCorrectInput() {
        String text = inputField.getText().trim();
        if (text.matches("[-+]?\\d+") && Integer.parseInt(text) <= 5 && Integer.parseInt(text) >= 1) return true;
        else return false;
    }
}
