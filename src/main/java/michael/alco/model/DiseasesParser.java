package michael.alco.model;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DiseasesParser {

    private static final String path = "src/main/java/michael/alco/model/diseases.txt";
    public static LinkedHashMap<String, ArrayList<String>> allDiseases = new LinkedHashMap<>();


    public static void readFile() throws IOException {
        FileInputStream fis = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

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
