import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.net.URL;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

// import org.fusesource.jansi; need to get the jar

public class Main {




    public static void main(String[] args) throws Exception {

        //String imdbApiKey = System.getenv("IMDB_API_KEY");
        // Get the API key
        /*String key = "";
        try {
            Scanner scanner = new Scanner(new File("resources/key.properties"));
            key = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("API key was not found");
            e.printStackTrace();
        }*/

        String urlApiNASA = API.NASA_APOD.getUrl();
        // conexão http aceder api IMDB https://imdb-api.com/en/API/Top250Movies
        String urlApiImdb = API.IMDB_TOP_MOVIES.getUrl();
        ClientHttp clientHttp = new ClientHttp();
        String json = clientHttp.getData(urlApiNASA);
        

        // extrair dados que nos interessam (título, imagem, rating)
        ContentExtractor extractor = new NasaContentExtractor();
        List<Content> contentsList = extractor.contentExtractor(json);

        // exibir e manipular os dados
        File folder = new File("stickers/");
        folder.mkdir();
        StickerGenerator stickerGenerator = new StickerGenerator();


        for (int index=0; index<2;index++) {
            Content content = contentsList.get(index);
            InputStream inputStream = new URL(content.urlImage())
                    .openStream();
            String filename = "stickers/" + content.title() + ".png";



            stickerGenerator.generate(inputStream, filename, "TOPZERA");


            //stickerGenerator.generate(inputStream, "stickers/" + filename, inputStreamStamp ,stickerText);


            /*System.out.println("\u001b[1mTítulo:\u001b[m " + content.get("title"));
            System.out.println("\u001b[1mPoster:\u001b[m " + content.get("image"));
            System.out.println(content.get("imDbRating"));

            int starNumber = numberStarsDouble.intValue();
            StringBuilder ratStar = new StringBuilder();
            for(int i = 1; i < starNumber; i++){
                ratStar.append("⭐");
            }
            while (10 - starNumber > 0){
                ratStar.append("✩");
                starNumber++;
            }
            System.out.println(ratStar.toString());*/
        }
    }
}