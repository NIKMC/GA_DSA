import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import General.*;
import javafx.util.Pair;

/**
 * Created by NIKMC on 07-Oct-16.
 */
public class Main {

    public static void main(String[] args){
       // new Loading().loadImage();

        long start = 0, finish = 0;
        try {
            Pair<Integer, Integer> startFinish = MapScanner.findStartFinish("Labirint.bmp");
            start = System.currentTimeMillis();
            BFS bfs = new BFS();
            bfs.run(MapScanner.scan("Labirint.bmp", 4),startFinish);
            BFS.DrawTrack("Labirint.bmp",bfs.run(MapScanner.scan("Labirint.bmp", 4),startFinish));
            finish = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("time in millis = " + (finish - start));



    }


    //dsdvsdv
}
