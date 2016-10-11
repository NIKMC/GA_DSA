package A_star.Algorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import General.*;
import ImageFileReader.*;

/**
 * Created by Konstantin on 10.10.2016.
 */
public class PathFinder {
    static final int IMG_WIDTH = 480;
    static final int IMG_HEIGHT = 480;

    public static void Start(String path, Point startCoord, Point finCoord){
        try {
            LinkedList<Integer> [] initGraph = MapScanner.scan(path);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<Node> openList;
        ArrayList<Integer> closedList;
        double auxG, auxH, auxF;
        int singleCoord;

        openList.add(new Node())
    }

    private static int TwoDimToOneDim(Point point, int width){
        System.out.println(point.getX() + point.getY() * width);
        return point.getX() + point.getY() * width;
    }

    private static Integer [] OneDimToTwoDim(int coord, int width){
        Integer []xy = new Integer[2];
        xy[0] = coord % width;
        xy[1] = coord / width;
        return xy;
    }
}
