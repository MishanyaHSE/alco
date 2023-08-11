package michael.alco.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DiseasesBase {
    public static int numberOfYears;
    public static int percent;
    public static LinkedHashMap<Button, Label> checkedDiseasesSaver = new LinkedHashMap<>();
    public static ArrayList<String> pictures = new ArrayList<>();
}
