package michael.alco.model;

import michael.alco.AlcoApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class DiseasesParser {

    private static final String path = "src/main/java/michael/alco/model/diseases.txt";
    public static LinkedHashMap<String, ArrayList<String>> allDiseases = new LinkedHashMap<>();


    public static void readFile() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(AlcoApplication.class.getResourceAsStream("diseases.txt"))));

        String line;
        while ((line = br.readLine()) != null) {
            ArrayList<String> diseases = new ArrayList<>();
            for (int i = 1; i < line.split(",").length; ++i){
                diseases.add(line.split(",")[i]);
            }
            allDiseases.put(line.split(",")[0], diseases);
        }
    }
}
