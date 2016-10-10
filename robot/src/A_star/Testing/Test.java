package A_star.Testing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import A_star.Algorithm.PathFinder;
import General.Point;

/**
 * Created by Konstantin on 09.10.2016.
 */
public class Test {
    public static void main(String [] args){
        PathFinder.Start("robot/src/Labirint.bmp", new Point(0,0), new Point(0,0));
//        SpoilImage();
    }


    private static void SpoilImage(){
        File f = new File("robot/src/Labirint.bmp");
        int pixelColor = 0;
        try {
            BufferedImage image = ImageIO.read(f);
            image.setRGB(2,2, pixelColor);
            image.setRGB(3,2, pixelColor);
            image.setRGB(2,3, pixelColor);
            image.setRGB(3,3, pixelColor);
            image.setRGB(4,4, pixelColor);
            image.setRGB(5,5, pixelColor);
            image.setRGB(6,5, pixelColor);
            image.setRGB(7,5, pixelColor);
            image.setRGB(8,5, pixelColor);
            ImageIO.write(image, "png", f);
        } catch (IOException e) {

        }
    }
}
