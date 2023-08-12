package michael.alco.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import michael.alco.AlcoApplication;
import michael.alco.model.DiseasesBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ImagesController implements Initializable {

    @FXML
    private ImageView picturesImageView;

    private int currentImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentImage = 0;
        displayCurrentPicture(currentImage);
    }

    @FXML
    private void nextPictureClick() {
        if (currentImage + 1 < DiseasesBase.pictures.size()) {
            currentImage++;
            displayCurrentPicture(currentImage);
        }
    }

    @FXML
    private void previousPictureClick() {
        if (currentImage - 1 > -1) {
            currentImage--;
            displayCurrentPicture(currentImage);
        }
    }

    @FXML
    private void backButtonClick() {
        try {
            Utility.changeScene("brain-view.fxml", picturesImageView, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCurrentPicture(int index) {
        Image image = new Image(Objects.requireNonNull(AlcoApplication.class.getResourceAsStream(DiseasesBase.pictures.get(index))));
        picturesImageView.setImage(image);
        picturesImageView.setFitHeight(600);
        picturesImageView.setFitWidth(800);
        picturesImageView.setPreserveRatio(true);
    }
}
