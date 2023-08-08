package michael.alco.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import michael.alco.model.DiseasesBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecommendationController implements Initializable {
    @FXML
    Label headLabel;

    @FXML
    Text contentText;

    @FXML
    Label effectiveLabel;

    @FXML
    private void previousPageCLick() {
        try {
            Utility.changeScene("years-view.fxml", headLabel, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextPageClick() {
        try {
            Utility.changeScene("brain-view.fxml", effectiveLabel, 800,600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextForHeadLabel();
        setContentText();
        setTextForEffectiveLabel();
    }

    private void setTextForHeadLabel() {
        if (DiseasesBase.numberOfYears == 1) {
            headLabel.setText("Компьютерный рецепт воздействия рассчитанный на " + DiseasesBase.numberOfYears + " год.");
        } else if (DiseasesBase.numberOfYears == 5) {
            headLabel.setText("Компьютерный рецепт воздействия рассчитанный на " + DiseasesBase.numberOfYears + " лет.");
        } else {
            headLabel.setText("Компьютерный рецепт воздействия рассчитанный на " + DiseasesBase.numberOfYears + " года.");
        }
    }

    private void setContentText() {
        switch (DiseasesBase.numberOfYears) {
            case 1 -> contentText.setText("""
                        1-й день:
                    МРТ на точки: РСЗ (инь-тан); Т20; Е36 + г/н лазер на кубитальные вены.
                        На 1-й год:
                    МРТ на точки: Е42; P7;
                    """);
            case 2 -> contentText.setText("""
                        1-й день:
                    МРТ на точки: РСЗ (инь-тан); Т20; Е36 + г/н лазер на кубитальные вены.
                        На 1-й год:
                    МРТ на точки: Е42; P7;
                        На 2-й год:
                    МРТ на точки: VB20; RP6; + г/н лазер на кубитальные вены.
                    """);
            case 3 -> contentText.setText("""
                        1-й день:
                    МРТ на точки: РСЗ (инь-тан); Т20; Е36 + г/н лазер на кубитальные вены.
                        На 1-й год:
                    МРТ на точки: Е42; P7;
                        На 2-й год:
                    МРТ на точки: VB20; RP6; + г/н лазер на кубитальные вены.
                        На 3-й год:
                    МРТ на точки: F8; TR15;
                    """);
            default -> contentText.setText("Не удалось назначить лечение");
        }
    }

    private void setTextForEffectiveLabel() {
        effectiveLabel.setStyle("-fx-background-color:#00bfff");
        if (DiseasesBase.percent <= 35) {
            effectiveLabel.setText("Внимание доктор! Процент поражения менее " + DiseasesBase.percent + "%\n Гарантия эффективности лечения до 98%");
        } else {
            effectiveLabel.setText("Внимание доктор! Процент поражения более 35%.\n Гарантия эффективности лечения менее 30%.");
        }
    }
}
