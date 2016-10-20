package A_star.Algorithm;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import General.*;
import ImageFileReader.*;
import javafx.util.Pair;

import java.awt.Color;

import javax.imageio.ImageIO;

/**
 * Created by Konstantin on 10.10.2016.
 */
public class PathFinder {
    static int IMG_WIDTH ;
    static int IMG_HEIGHT;
    static LinkedList<Integer> [] initGraph;
    static Node [] nodeGraph;

    public static void Start(String path){
        ImageDimensionsDetermination(path);
        Point startCoord, finCoord;
        Pair<Integer, Integer> p = MapScanner.findStartFinish(path);
        startCoord = new Point(p.getKey()%IMG_WIDTH, p.getKey()/IMG_WIDTH );
        finCoord = new Point(p.getValue()%IMG_WIDTH, p.getValue()/IMG_WIDTH);
        System.out.println(startCoord.getX() + " " + startCoord.getY());
        System.out.println(finCoord.getX() + " " + finCoord.getY());
//        startCoord = new Point(5, 5 );
//        finCoord = new Point(1910, 1910);
        nodeGraph = CreateNodeArray(path,finCoord);
        Node goalNode = GoToGoal(startCoord, finCoord);
        DrawTrack(path, goalNode);
    }

    private static void ImageDimensionsDetermination(String path){
        File f = new File(path);
        try {
            BufferedImage image = ImageIO.read(f);
            IMG_WIDTH = image.getWidth();
            IMG_HEIGHT = image.getHeight();
        } catch (IOException e) {

        }
    }

    private static void DrawTrack(String path, Node finalNode){
        File f = new File(path);
        int pixelColor = new Color(255,0,0).getRGB();
        try {
            BufferedImage image = ImageIO.read(f);
            Node bfrNode = finalNode;
            while(bfrNode != null){
                image.setRGB(bfrNode.getPosition().getX(), bfrNode.getPosition().getY(), pixelColor);
                bfrNode = bfrNode.getParent();
            }
            image.setRGB(2,2, pixelColor);
            ImageIO.write(image, "png", f);
        } catch (IOException e) {

        }
    }

    private static Node GoToGoal(Point startCoord, Point finCoord){
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        Node nodeBfr, childNode;
        int iteratorThroughLinks;
        int leastFNodeIndex;
        nodeBfr = new Node(nodeGraph[TwoDimToOneDim(startCoord, IMG_WIDTH)]);
        nodeBfr.setG(0.0);
        openList.add(nodeBfr);

        //start picking nodes
        while(!openList.isEmpty()){
            leastFNodeIndex = GetLeastFNodeIndex(openList);
            nodeBfr = openList.remove(leastFNodeIndex);
            if(isPresentOnList(closedList, nodeBfr.getPosition()))
                continue;
            for(iteratorThroughLinks = 0; iteratorThroughLinks < 8; iteratorThroughLinks++){
                if(nodeBfr.getLinks()[iteratorThroughLinks] != -1) {
                    childNode = new Node(nodeGraph[nodeBfr.getLinks()[iteratorThroughLinks]]);
                    childNode.setParent(nodeBfr);
                    if(childNode.getPosition().PositionEquals(finCoord))
                        return childNode;
                    childNode.setG(nodeBfr);

                    if(BetterFSamePosOnList(openList, childNode.getF(), childNode.getPosition()))
                        continue;
                    if(BetterFSamePosOnList(closedList, childNode.getF(), childNode.getPosition()))
                        continue;
                    else
                        openList.add(childNode);
                }
            }
            closedList.add(nodeBfr);
        }
        System.out.println("Path not found");
        return null;
    }

    private static boolean isPresentOnList(ArrayList<Node> list, Point position){
        for(Node iterator : list) {
            if(iterator.getPosition().PositionEquals(position))
                return true;
        }
            return false;
    }

    private static boolean BetterFSamePosOnList(ArrayList<Node> list, double F, Point position){
        for(Node iterator : list) {
            if(iterator.getF() < F && iterator.getPosition().PositionEquals(position))
                return true;
        }
        return false;
    }

    private static int GetLeastFNodeIndex(ArrayList<Node> openList){
        Node leastFnode = openList.get(0);
        int leastFNodeIndex = 0, i = 0;
        for(Node iterator : openList){
            if (iterator.getF() < leastFnode.getF()) {
                leastFnode = iterator;
                leastFNodeIndex = i;
            }
            i++;
        }
        return leastFNodeIndex;
    }

    private static Node GetLeastFNode(ArrayList<Node> openList){
        Node leastFnode = openList.get(0);
        for(Node iterator : openList){
            if (iterator.getF() < leastFnode.getF())
                leastFnode = iterator;
        }
        return leastFnode;
    }



    private static Node [] CreateNodeArray(String path, Point finCoord){
        Node [] nodeGraph;
        GetImageDimension(path);
        try {
            initGraph = MapScanner.scan(path, 5);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        nodeGraph = new Node[initGraph.length];
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
