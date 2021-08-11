import java.util.*;
import java.util.function.Consumer;

public class JsonResponse {

    private Map<String, List<String>> stationsToPrint = new HashMap<>();
    private List<Line> linesToPrint = new ArrayList<>();


    public Map<String, List<String>> getStations() {
        return stationsToPrint;
    }

    public void setStations(Map<String, List<String>> stations) {
        this.stationsToPrint = stations;
    }

    public List<Line> getLines() {
        return linesToPrint;
    }

    public void setLines(List<Line> lines) {
        this.linesToPrint = lines;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "stations=" + stationsToPrint +
                ", lines=" + linesToPrint +
                '}';
    }
}