package michael.alco.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImagesController implements Initializable {
    @FXML
    ImageView imageView;
    @FXML
    Circle circle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image image = new Image(new FileInputStream("src/main/java/michael/alco/controllers/splash.jpg"));
            imageView.setImage(image);
            imageView.setFitHeight(800);
            imageView.setFitWidth(600);
            imageView.setPreserveRatio(true);
            circle.setRadius(50);
            circle.setFill(Color.RED);
            circle.setOpacity(0.3);
            circle.setStrokeWidth(2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backClick() {
        try {
            Utility.changeScene("recommendation-view.fxml", imageView, 800, 600);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void nextClick() {

    }
}
