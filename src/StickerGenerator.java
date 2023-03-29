import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    public void generate(InputStream inputStream, String fileName) throws Exception{
        // leitura da imagem
        // InputStream inputStream = new FileInputStream("resources/filme_maior.jpg");
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        BufferedImage original = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e tamanho novo
        int width = original.getWidth();
        int height = original.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //copiar imagem original para nova imagem
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(original,0,0,null);

        // configurar fonte
        Font font= new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        //escrever frase na nova imagem
        graphics.drawString("TOP",width/2,newHeight - 100);

        //escrever nova imagem num ficheiro
        ImageIO.write(newImage,"png", new File(fileName));
    }


}
