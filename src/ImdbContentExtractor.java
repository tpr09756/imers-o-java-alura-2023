import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor{

    JsonParser parser = new JsonParser();

    public List<Content> contentExtractor(String json){

        List<Map<String,String>> attributesList = parser.parse(json);



        return attributesList.stream()
                .map(att -> new Content(att.get("title"), att.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg")))
                .toList();
    };
    /*
        String stickerText;
        InputStream inputStreamStamp;
        Double numberStarsDouble = Double.parseDouble(attribute.get("imDbRating"));
        if (numberStarsDouble >= 8.0 ){
            stickerText = "TOPZERA";
            inputStreamStamp = new FileInputStream("resources/approved.png");
        } else {
            stickerText = "NOT APROVED";
            inputStreamStamp = new FileInputStream("resources/disapproved.png");
        }*/

}
