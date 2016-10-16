import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NIKMC on 07-Oct-16.
 */
public class Main {

    public static void main(String[] args){
        //new Loading().loadImage();
        try {
            new BFS().run(MapScanner.scan("Labirint.bmp", 5));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //dsdvsdv
}
