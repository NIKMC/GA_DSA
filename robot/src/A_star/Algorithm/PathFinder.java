package A_star.Algorithm;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import General.*;
import ImageFileReader.*;

import javax.imageio.ImageIO;

/**
 * Created by Konstantin on 10.10.2016.
 */
public class PathFinder {
    static int IMG_WIDTH = 480;
    static int IMG_HEIGHT = 480;
    static LinkedList<Integer> [] initGraph;
    static Node [] nodeGraph;

    public static void Start(String path, Point startCoord, Point finCoord){
        nodeGraph = CreateNodeArray(path,finCoord);
        ArrayList<Node> openList;
        ArrayList<Integer> closedList;
        double auxG, auxH, auxF;
        int singleCoord;

//        openList.add(new Node())
    }

    private static Node [] CreateNodeArray(String path, Point finCoord){
        GetImageDimension(path);
        try {
            initGraph = MapScanner.scan(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Node [] nodeGraph = new Node[initGraph.length];
        int i=0, j;
        int []xy = {0,0};
        for(LinkedList<Integer> iterator : initGraph){
            nodeGraph[i] = new Node();
            xy = OneDimToTwoDim(i, IMG_WIDTH);
            nodeGraph[i].setPosition(new Point(xy[0], xy[1]));
            nodeGraph[i].CalculateH(finCoord);
            j = 0;
            while (!iterator.isEmpty()){
                nodeGraph[i].AddLink(j++,iterator.remove());
            }
            i++;
        }
        return nodeGraph;
    }

    static private void GetImageDimension(String path ){
        File imgFile = new File(path);
        BufferedImage labirinthImage;
        try {
            labirinthImage = ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if(labirinthImage != null) {
            IMG_WIDTH = labirinthImage.getWidth();
            IMG_HEIGHT = labirinthImage.getHeight();
        }
    }

    private static int TwoDimToOneDim(Point point, int width){
        System.out.println(point.getX() + point.getY() * width);
        return point.getX() + point.getY() * width;
    }

    private static int [] OneDimToTwoDim(int coord, int width){
        int []xy = new int[2];
        xy[0] = coord % width;
        xy[1] = coord / width;
        return xy;
    }

    private void AddInitNodeToOpenList(Point startCoord, LinkedList<Integer> [] initGraph, ArrayList<Node> openList){
//        int xInitCoord, yInitCoord;
//        xInitCoord =
    }
}
