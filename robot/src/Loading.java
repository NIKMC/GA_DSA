import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NIKMC on 08-Oct-16.
 */
public class Loading {

    public int[][] loadImage(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Labirint.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int w = image.getWidth();
        int h = image.getHeight();
        int[][] masPixel = new int[w][h];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++) {
                masPixel[j][i] = image.getRGB(j, i);
                System.out.print(masPixel[j][i] + " ");
            }
            System.out.println("");
        }
        return masPixel;
    }

}
