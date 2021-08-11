import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static Path fileJson = Path.of("src", "main", "resources", "stations.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        List<Line> listLines = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements namesOfLines = doc.getElementsByClass("js-metro-line");
        namesOfLines.forEach(el -> {
            listLines.add(new Line(el.attr("data-line"), el.text()));
        });

        Elements namesOfStations = doc.getElementsByClass("js-metro-stations");
        namesOfStations.forEach(el -> {
            el.children().forEach(element -> {
                for (Line listLine : listLines) {
                    if (listLine.getNumber().equals(el.attr("data-line"))) {
                        listLine.addStation(new Station(element.getElementsByClass("name").text(), listLine));
                    }
                }
            });
        });

        List<String> stationsNameList;
        Map<String, List<String>> mapLinesAndStations = new HashMap<>();
        for (Line lineTime : listLines) {
            stationsNameList = new ArrayList<>();
            for (Station station : lineTime.getStations()) {
                stationsNameList.add(station.getName());
            }
            mapLinesAndStations.put(lineTime.getNumber(), stationsNameList);
        }
        // MAP для линиий
        HashMap<String, String> mapLines = null; //new HashMap<>();
        // LIST с объектами (линиями)
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        for (Line line : listLines) {
            mapLines = new HashMap<>();
            mapLines.put("number", line.getNumber());
            mapLines.put("name", line.getName());
            hashMapList.add(mapLines);
        }
        // МАР для json - ЗАПИСЬ В ФАЙЛ ( ПОСЛЕДНИЙ ПУНКТ)
        Map<String, Object> mapToJson = new HashMap<>();
        mapToJson.put("stations", mapLinesAndStations);
        mapToJson.put("lines", hashMapList);

        // Записали в файл
        try (FileWriter fw = new FileWriter(fileJson.toFile())) {

            GSON.toJson(mapToJson, fw);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(fileJson.toFile())) {
            JsonResponse jr = GSON.fromJson(reader, JsonResponse.class);
            for (Map.Entry<String, List<String>> entry : jr.getStations().entrySet()) {
                System.out.println("Номер линии: " + entry.getKey() + ", количество станций на линии == " + entry.getValue().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
