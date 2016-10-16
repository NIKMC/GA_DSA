package ImageFileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Stanislav on 09.10.2016.
 */
public class MapScanner {
    /***
     *
     * @param path - path to Labirint.bmp
     * @return Array of linkedListes
     * @throws IOException
     */
    private static int width;
    private static int height;
    private static int _robotWidth;
    private static long _BLACK = -16000000;

    public static LinkedList<Integer>[] scan(String path, int robotWidth) throws IOException {

        LinkedList<Integer> [] graph = null;
        File f = new File(path);
        BufferedImage image = ImageIO.read(f);
        _robotWidth = robotWidth;

        if(image != null){
            width = image.getWidth();
            height = image.getHeight();
            graph = new LinkedList[height*width];  // graphh is a matrix -> array, matrix[i,j] = graph[i*width + j]
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<Integer>();
            }

            for (int i = 0; i < height - _robotWidth; i++) {
                for (int j = 0; j < width - _robotWidth; j++) {
                    if(image.getRGB(j,i) > _BLACK) {
                        if (i > 0 && image.getRGB(j, i - 1) > _BLACK && _enoughSpace(j, i, j, i - 1, image)) { // upper pixel
                            graph[i * width + j].add((i - 1) * width + j);
                        }
                        else graph[i * width + j].add(-1);
                        if (i > 0 && j < width - 1 && image.getRGB(j + 1, i - 1) > _BLACK && _enoughSpace(j, i, j+1, i - 1, image)) { // upper right pixel
                            graph[i * width + j].add((i - 1) * width + j + 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (j < width - 1 && image.getRGB(j + 1, i) > _BLACK && _enoughSpace(j, i, j, i - 1, image)) { // right pixel
                            graph[i * width + j].add(i * width + j + 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (i < height - 1 && j < width - 1 && image.getRGB(j + 1, i + 1) > _BLACK && _enoughSpace(j, i, j+1, i + 1, image)) { // lower right pixel
                            graph[i * width + j].add((i + 1) * width + j + 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (i < height - 1 && image.getRGB(j, i + 1) > _BLACK && _enoughSpace(j, i, j, i + 1, image)) { // lower pixel
                            graph[i * width + j].add((i + 1) * width + j);
                        }
                        else graph[i * width + j].add(-1);
                        if (i < height - 1 && j > 0 && image.getRGB(j - 1, i + 1) > _BLACK && _enoughSpace(j, i, j - 1, i + 1, image)) { // lower left pixel
                            graph[i * width + j].add((i + 1) * width + j - 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (j > 0 && image.getRGB(j - 1, i) > _BLACK && _enoughSpace(j, i, j - 1, i, image)) { // left pixel
                            graph[i * width + j].add(i * width + j - 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (j > 0 && i > 0 && image.getRGB(j - 1, i - 1) > _BLACK && _enoughSpace(j, i, j - 1, i - 1, image)) { // upper left pixel
                            graph[i * width + j].add((i-1) * width + j - 1);
                        }
                        else graph[i * width + j].add(-1);
                    }

                }
            }
        }
        /*for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i]);
        }*/
        return graph;
    }


    private static boolean _enoughSpace (int curX, int curY, int newX, int newY,  BufferedImage image){
        int shiftX = newX - curX;
        int shiftY = newY - curY;
        int oppositeX = curX + _robotWidth;
        int oppositeY = curY + _robotWidth;
        if(oppositeX +shiftX > width-1 || oppositeY +shiftY > height-1)
            return false;
        for (int i = 0; i <= _robotWidth; i++) {
            if(curX+i +shiftX < 0 || curY+i +shiftY < 0 || /*curX+i +shiftX > width-1 || curY+i +shiftY > height-1 ||*/
                    image.getRGB(curX+i +shiftX, curY) <= _BLACK || image.getRGB(curX, curY+i +shiftY) <= _BLACK )
                return false;
            if(oppositeX-i +shiftX > width-1 || oppositeY-i +shiftY > height-1 ||
                    image.getRGB(oppositeX-i +shiftX, oppositeY) <= _BLACK || image.getRGB(oppositeX, oppositeY-i +shiftY) <= _BLACK)
                return false;
        }
        return true;
    }



    public static LinkedList<Integer>[] scanTest(String path, int robotWidth) throws IOException {

        LinkedList<Integer> [] graph = null;
        File f = new File(path);
        BufferedImage image = ImageIO.read(f);
        _robotWidth = robotWidth;

        if(image != null){
            width = image.getWidth();
            height = image.getHeight();
            graph = new LinkedList[height*width];  // graphh is a matrix -> array, matrix[i,j] = graph[i*width + j]
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<Integer>();
            }

            for (int i = 432; i < 436; i++) {
                for (int j = 432; j < 436; j++) {
                    System.out.println(i * width + j + ". " + image.getRGB(j,i));
                    if(image.getRGB(j,i) > _BLACK) {
                        if (i > 0 && image.getRGB(j, i - 1) > _BLACK && _enoughSpace(j, i, j, i - 1, image)) { // upper pixel
                                graph[i * width + j].add((i - 1) * width + j);
                        }
                        else graph[i * width + j].add(-1);
                        if (i > 0 && j < width - 1 && image.getRGB(j + 1, i - 1) > _BLACK && _enoughSpace(j, i, j+1, i - 1, image)) { // upper right pixel
                                graph[i * width + j].add((i - 1) * width + j + 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (j < width - 1 && image.getRGB(j + 1, i) > _BLACK && _enoughSpace(j, i, j, i - 1, image)) { // right pixel
                                graph[i * width + j].add(i * width + j + 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (i < height - 1 && j < width - 1 && image.getRGB(j + 1, i + 1) > _BLACK && _enoughSpace(j, i, j+1, i + 1, image)) { // lower right pixel
                                graph[i * width + j].add((i + 1) * width + j + 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (i < height - 1 && image.getRGB(j, i + 1) > _BLACK && _enoughSpace(j, i, j, i + 1, image)) { // lower pixel
                                graph[i * width + j].add((i + 1) * width + j);
                        }
                        else graph[i * width + j].add(-1);
                        if (i < height - 1 && j > 0 && image.getRGB(j - 1, i + 1) > _BLACK && _enoughSpace(j, i, j - 1, i + 1, image)) { // lower left pixel
                                graph[i * width + j].add((i + 1) * width + j - 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (j > 0 && image.getRGB(j - 1, i) > _BLACK && _enoughSpace(j, i, j - 1, i, image)) { // left pixel
                                graph[i * width + j].add(i * width + j - 1);
                        }
                        else graph[i * width + j].add(-1);
                        if (j > 0 && i > 0 && image.getRGB(j - 1, i - 1) > _BLACK && _enoughSpace(j, i, j - 1, i - 1, image)) { // upper left pixel
                                graph[i * width + j].add((i-1) * width + j - 1);
                        }
                        else graph[i * width + j].add(-1);
                    }

                }
            }
        }
        /*for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i]);
        }*/
        return graph;
    }
}
