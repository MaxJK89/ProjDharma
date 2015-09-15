import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by angboty on 9/15/2015.
 */
public class GetColor {

    public static String getColorFromImage(File file, int x, int y) {
        try {
            BufferedImage image = ImageIO.read(file);
            int c = image.getRGB(x, y);
            int red = (c & 0x00ff0000) >> 16;
            int green = (c & 0x0000ff00) >> 8;
            int blue = c & 0x000000ff;
//            Color color = new Color(red, green, blue);
//            System.out.println(String.format("#%02x%02x%02x", red, green, blue));

            return String.format("#%02x%02x%02x", red, green, blue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
