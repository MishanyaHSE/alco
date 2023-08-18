package michael.alco.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import michael.alco.AlcoApplication;
import michael.alco.model.DiseasesBase;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BrainController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Label brainLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button nextPageButton;

    private boolean isBlack = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (DiseasesBase.percent == 25) {
            addBrainAnimation("brain1_0.png", "brain1_1.png");
        } else if (DiseasesBase.percent == 35){
            addBrainAnimation("brain2_0.png", "brain2_1.png");
        } else if (DiseasesBase.percent == 40) {
            addBrainAnimation("brain3_0.png", "brain3_1.png");
        } else if (DiseasesBase.percent == Integer.MAX_VALUE) {
            addBrainAnimation("brain4_0.png", "brain4_1.png");
            nextPageButton.setVisible(false);
            closeButton.setVisible(true);
        }
        addTextToLabel();
    }

    @FXML
    private void closeAppClick() {
        System.exit(0);
    }

    @FXML
    private void backClick() {
        try {
            Utility.changeScene("recommendation-view.fxml", imageView, 800, 600);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextClick() {
        try {
            Utility.changeScene("images-view.fxml", imageView, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addBrainImage(String name) {
        Image image = new Image(Objects.requireNonNull(AlcoApplication.class.getResourceAsStream(name)));
        imageView.setImage(image);
        imageView.setFitHeight(481);
        imageView.setFitWidth(668);
        imageView.setPreserveRatio(false);
    }

    private void addBrainAnimation(String first, String second) {
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(80), actionEvent -> {
            if (isBlack) {
                addBrainImage(first);
                isBlack = false;
            } else {
                addBrainImage(second);
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
        } else if (DiseasesBase.percent == 40) {
            brainLabel.setText("Красным отмечены зоны поражения. Поражено более 35%. " +
                    "Гарантия эффективности лечения менее 30%.");
        } else {
            brainLabel.setText("Красным отмечены зоны поражения. Поражено более 40%. " +
                    "Необходима дополнительная детоксикация.");
        }
    }
}
