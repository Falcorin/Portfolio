import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;

public class SiteChildLink extends RecursiveTask<List<String>> {
    private final String url;
    private static final String TAB = "\t";
    private Vector<String> log = new Vector<>();

    public SiteChildLink(String url) {
        this.url = url;
        if (!isLastSlashHere(url)) {
            url = url.concat("/");
        }
        log.add(cleanUrl(url));
    }

    @Override
    protected List<String> compute() {
        List<SiteChildLink> subTasks = new ArrayList<>();
        loadHrefList().forEach(href -> {
            if (!log.contains(cleanUrl(href))) {
                SiteChildLink siteChildLink = new SiteChildLink(href);
                subTasks.add(siteChildLink);
                siteChildLink.fork();
            }
        });

        List<String> resultList = new ArrayList<>();
        resultList.add(url);
        subTasks.forEach(results -> results.join().forEach(result -> resultList.add(addTab(url) + url)));
        return resultList;
    }

    private List<String> loadHrefList() {
        List<String> listHref = new ArrayList<>();
        try {
            int[] ints = new Random().ints(1, 100, 500).toArray();
            Thread.sleep(ints[0]);
            Document document = Jsoup.connect(url).get();
            document.select("a[href^=\"/\"],[href^=\"" + url + "\"]").forEach((Element element) -> {
                String href = element.attr("abs:href");
                if (href.indexOf('#') < 0 && isLastSlashHere(href) && cleanUrl(href).indexOf(log.get(0)) == 0) {
                    listHref.add(addTab(url) + href);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHref;
    }

    private String addTab(String url) {
        int pos = url.indexOf("http");
        return (pos > 0) ? url.substring(0, pos - 1) + TAB : TAB;
    }

    private boolean isLastSlashHere(String url) {
        return url.lastIndexOf("/") == url.length() - 1;
    }

    private String cleanUrl(String url) {
        return url.replace("://www.", "://").replaceAll("[" + TAB + "]+", "");
    }
}
