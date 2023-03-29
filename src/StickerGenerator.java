import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    public void generate(InputStream inputStream, String fileName,InputStream inputStreamStamp, String text) throws Exception{
        // leitura da imagem
        // InputStream inputStream = new FileInputStream("resources/filme_maior.jpg");
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        BufferedImage original = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e tamanho novo
        int width = original.getWidth();
        int height = original.getHeight();
        float newHeight = height * 1.2f;
        BufferedImage newImage = new BufferedImage(width, (int)newHeight, BufferedImage.TRANSLUCENT);

        //copiar imagem original para nova imagem

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(original,0,0,null);

        BufferedImage stamp = ImageIO.read(inputStreamStamp);
        int posStamp = height - stamp.getHeight();
        graphics.drawImage(stamp,0,posStamp,null );
        // configurar fonte
        Font font= new Font("Impact", Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        //escrever frase na nova imagem

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(text,graphics);
        int textWidth = (int) rectangle.getWidth();
        int textHeight = (int) rectangle.getHeight();

        int posTextX = (width - textWidth)/2;
        float posTextY = (newHeight - height)/2 + height + textHeight/3;

        graphics.drawString(text,posTextX,posTextY);

        // Outline

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posTextX,posTextY);

        BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        //escrever nova imagem num ficheiro
        ImageIO.write(newImage,"png", new File(fileName));
    }


}
