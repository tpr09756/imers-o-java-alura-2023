import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public enum API {
    IMDB_TOP_MOVIES("https://imdb-api.com/en/API/Top250Movies/" + System.getenv("IMDB_API_KEY"), new ImdbContentExtractor()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14", new NasaContentExtractor());

    private String url;
    private ContentExtractor extractor;

    API(String url, ContentExtractor extractor){
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}
