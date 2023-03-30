import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor{
    JsonParser parser = new JsonParser();

    public List<Content> contentExtractor(String json){

        List<Map<String,String>> attributesList = parser.parse(json);

        return attributesList.stream()
                .map(attribute -> new Content(attribute.get("title"), attribute.get("url")))
                .toList();
    };
}
