
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String DONE = "Work done, for file: ";

    public static void main(String[] args) {
        String file = Path.of("src", "main", "resources", "file.txt").toString();
        String url = "https://lenta.ru/";
        List<String> mapSite = new ForkJoinPool().invoke(new SiteChildLink(url));
        try {
            Files.write(Paths.get(file), mapSite);
            System.out.println(DONE.concat(file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
