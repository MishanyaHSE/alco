package michael.alco.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import michael.alco.model.DiseasesParser;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER;

public class DiseasesController implements Initializable {

    @FXML
    private VBox boxForDiseases;

    @FXML
    private VBox boxForCheckedDiseases;

    @FXML
    private VBox boxForButtons;

    private LinkedHashMap<Button, Label> checkedDiseases = new LinkedHashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAndFillMenuButtons();
        addActionsToMenuButton();
        displayAllCheckedDiseases();
        System.out.println(checkedDiseases.keySet());
        System.out.println(checkedDiseases.values());
    }

    @FXML
    protected void nextPageClick(){
        try {
            Utility.changeScene("years-view.fxml", boxForDiseases, 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void clearClick(){
        checkedDiseases.clear();
        displayAllCheckedDiseases();
    }

    private void createAndFillMenuButtons() {
        for (String name: DiseasesParser.allDiseases.keySet()) {
            MenuButton disList = new MenuButton();
            disList.setPrefWidth(250);
            disList.setPrefHeight(40);
            disList.setText(name);
            disList.setTextAlignment(TextAlignment.CENTER);
            disList.setAlignment(CENTER);
            for (String disease: DiseasesParser.allDiseases.get(name)) {
                MenuItem item = new MenuItem(disease);
                disList.getItems().add(item);
            }
            boxForDiseases.getChildren().add(disList);
        }
    }

    private void addActionsToMenuButton() {
        for (Node menu: boxForDiseases.getChildren()){
            MenuButton m = (MenuButton) menu;
            for (MenuItem item: m.getItems()) {
                item.setOnAction(e -> {
                    checkedDiseases.put(createXButton(), createNewDiseaseLabel(item.getText()));
                    displayAllCheckedDiseases();
                });
            }
        }
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
            checkedDiseases.remove(btn);
            displayAllCheckedDiseases();
        });
    }

    private void displayAllCheckedDiseases() {
        boxForButtons.getChildren().setAll(checkedDiseases.keySet());
        boxForCheckedDiseases.getChildren().setAll(checkedDiseases.values());
        System.out.println(checkedDiseases.values());
        System.out.println(checkedDiseases.keySet());
    }
}
