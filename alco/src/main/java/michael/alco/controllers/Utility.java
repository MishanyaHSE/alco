package michael.alco.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import michael.alco.AlcoApplication;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Utility {

    protected static LinkedHashMap<Button, Label> checkedDiseasesSaver = new LinkedHashMap<>();

    protected static void changeScene(String fileName, Node element, int width, int height) throws IOException {
        Stage stage = (Stage) element.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AlcoApplication.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setScene(scene);
        stage.show();
    }
}
