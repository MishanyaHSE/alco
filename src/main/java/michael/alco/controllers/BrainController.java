package michael.alco.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import michael.alco.model.DiseasesBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrainController implements Initializable {

    @FXML
    ImageView imageView;

    @FXML
    Circle circle;

    @FXML
    Label brainLabel;

    private boolean isBlack = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            addBrainImage();
            addCircleWithAnimation();
            addTextToLabel();
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

    private void addBrainImage() {
        try {
            Image image = new Image(new FileInputStream("src/main/java/michael/alco/controllers/brain.png"));
            imageView.setImage(image);
            imageView.setFitHeight(440);
            imageView.setFitWidth(500);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addCircleWithAnimation() {
        if (DiseasesBase.percent == 25) {
            circle.setRadius(50);
        } else if (DiseasesBase.percent == 35) {
            circle.setRadius(70);
        }
        circle.setFill(Color.RED);
        circle.setOpacity(0.6);
        circle.setStrokeWidth(2);
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
            if (isBlack) {
                circle.setStroke(Color.WHITE);
                isBlack = false;
            } else {
                circle.setStroke(Color.BLACK);
                isBlack = true;
            }
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    private void addTextToLabel() {
        if (DiseasesBase.percent <= 35) {
            brainLabel.setText("Красным отмечены зоны поражения. Поражено менее " + DiseasesBase.percent +
                    "%, гарантия эффективности лечения более 98%.");
        } else {
            brainLabel.setText("Bad case");
        }
    }
}
