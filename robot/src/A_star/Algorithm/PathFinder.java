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
        Node goalNode = GoToGoal(startCoord, finCoord);
    }

    private static Node GoToGoal(Point startCoord, Point finCoord){
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        Node nodeBfr, childNode;
        int iteratorThroughLinks;
        int leastFNodeIndex;
        nodeBfr = nodeGraph[TwoDimToOneDim(startCoord, IMG_WIDTH)];
        nodeBfr.setG(0.0);
        openList.add(nodeBfr);

        //start picking nodes
        while(!openList.isEmpty()){
            leastFNodeIndex = GetLeastFNodeIndex(openList);
            nodeBfr = openList.remove(leastFNodeIndex);
            for(iteratorThroughLinks = 0; iteratorThroughLinks < 8; iteratorThroughLinks++){
                if(nodeBfr.getLinks()[iteratorThroughLinks] != -1) {
                    childNode = nodeGraph[nodeBfr.getLinks()[iteratorThroughLinks]];
                    childNode.setParent(nodeBfr);
                    if(childNode.getPosition().PositionEquals(finCoord))
                        return childNode;
                    childNode.setG(nodeBfr);

                    if(BetterFParentIsOnList(openList, childNode.getF(), childNode.getPosition()))
                        continue;
                    if(BetterFParentIsOnList(closedList, childNode.getF(), childNode.getPosition()))
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

    private static boolean BetterFParentIsOnList(ArrayList<Node> list, double F, Point childPosition){
        for(Node iterator : list) {
            for (int iteratorThroughLinks = 0; iteratorThroughLinks < 8; iteratorThroughLinks++) {
                if(iterator.getLinks()[iteratorThroughLinks] != -1 && nodeGraph[iterator.getLinks()[iteratorThroughLinks]].getPosition().PositionEquals(childPosition))
                    if(iterator.getF() < F)
                        return true;
            }
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
            initGraph = MapScanner.scan(path);
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
