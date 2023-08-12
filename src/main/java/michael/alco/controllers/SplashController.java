package michael.alco.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import michael.alco.AlcoApplication;
import michael.alco.model.DiseasesParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    @FXML
    private Button startButton;
    @FXML
    private ImageView splashImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
            Image image = new Image(Objects.requireNonNull(AlcoApplication.class.getResourceAsStream("splash.jpg")));
            splashImageView.setImage(image);
            splashImageView.setFitHeight(600);
            splashImageView.setFitWidth(800);
            splashImageView.setPreserveRatio(true);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Не удалось загрузить изображение!");
//        }
    }

    @FXML
    protected void startButtonClick() {
        try {
            DiseasesParser.readFile();
            Utility.changeScene("diseases-view.fxml", startButton, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}