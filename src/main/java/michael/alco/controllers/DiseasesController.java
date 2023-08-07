package michael.alco.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import michael.alco.model.DiseasesBase;
import michael.alco.model.DiseasesParser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER;

public class DiseasesController implements Initializable {

    @FXML
    private VBox boxForDiseases;

    @FXML
    private VBox boxForCheckedDiseases;

    @FXML
    private VBox boxForButtons;

    @FXML
    private Button backButton;

    @FXML
    private Label headLabel;

    private String currentHead = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAndFillMenuButtons();
        displayAllCheckedDiseases();
        backButton.setTextAlignment(TextAlignment.CENTER);
        for(Button btn: DiseasesBase.checkedDiseasesSaver.keySet()) {
            setActionForDeleteDiseaseButton(btn);
        }
    }

    @FXML
    private void nextPageClick(){
        try {
            Utility.changeScene("years-view.fxml", boxForDiseases, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextHeadClick() {
        boolean flag = false;
        boolean hasChanged = false;
        if (currentHead.equals("")) {
            currentHead = "Психологическая зависимость";
            createDiseaseButtonsFromHead(currentHead);
            headLabel.setText(currentHead);
            return;
        }
        for (String name: DiseasesParser.allDiseases.keySet()) {
            if(flag) {
                currentHead = name;
                createDiseaseButtonsFromHead(currentHead);
                headLabel.setText(currentHead);
                hasChanged = true;
                break;
            }
            if (currentHead.equals(name)){
                flag = true;
                currentHead = "";
            }
        }
        if (!hasChanged) {
            backClick();
        }
    }

    @FXML
    private void clearClick(){
        DiseasesBase.checkedDiseasesSaver.clear();
        displayAllCheckedDiseases();
    }

    @FXML
    private void backClick() {
        boxForDiseases.getChildren().clear();
        headLabel.setText("Список симптомов:");
        currentHead = "";
        createAndFillMenuButtons();
    }

    private void createAndFillMenuButtons() {
        for (String name: DiseasesParser.allDiseases.keySet()) {
           Button headButton = createOneDiseaseButton(name);
            headButton.setOnAction(actionEvent -> {
                headLabel.setText(name + ":");
                currentHead = name;
                createDiseaseButtonsFromHead(currentHead);
            });
            boxForDiseases.getChildren().add(headButton);
        }
    }

    private Button createOneDiseaseButton(String text) {
        Button button = new Button();
        button.setPrefWidth(250);
        button.setPrefHeight(40);
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setAlignment(CENTER);
        return button;
    }

    private void createDiseaseButtonsFromHead(String name) {
        ArrayList<Button> diseases = new ArrayList<>();
        for (String diseaseName: DiseasesParser.allDiseases.get(name)) {
            Button oneDisease = createOneDiseaseButton(diseaseName);
            addActionsToDiseaseButton(oneDisease);
            diseases.add(oneDisease);
            boxForDiseases.getChildren().setAll(diseases);
        }
    }

    private void addActionsToDiseaseButton(Button btn) {
        btn.setOnAction(e -> {
            DiseasesBase.checkedDiseasesSaver.put(createXButton(), createNewDiseaseLabel(btn.getText()));
            displayAllCheckedDiseases();
        });
    }

    private Label createNewDiseaseLabel(String name) {
        Label disease = new Label(name);
        disease.setMinHeight(20);
        disease.setMaxHeight(20);
        return disease;
    }

    private Button createXButton() {
        Button btn = new Button("X");
        btn.setTextFill(Color.RED);
//                    btn.setStyle("-fx-font-size:0.5em");
        btn.setFont(Font.font(8));
        btn.setMinHeight(20);
        btn.setMaxHeight(20);
        btn.setMinWidth(20);
        btn.setMaxWidth(20);
        setActionForDeleteDiseaseButton(btn);
        return btn;
    }

    private void setActionForDeleteDiseaseButton(Button btn) {
        btn.setOnAction(e->{
            DiseasesBase.checkedDiseasesSaver.remove(btn);
            displayAllCheckedDiseases();
        });
    }

    private void displayAllCheckedDiseases() {
        boxForButtons.getChildren().setAll(DiseasesBase.checkedDiseasesSaver.keySet());
        boxForCheckedDiseases.getChildren().setAll(DiseasesBase.checkedDiseasesSaver.values());
    }
}
