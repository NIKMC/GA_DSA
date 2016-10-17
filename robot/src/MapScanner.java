import javafx.util.Pair;

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
    private static long _BLACK = -16000000;    // black : rgb < -16 000 000

    public static LinkedList<Edge>[] scan(String path, int robotWidth) throws IOException {

        LinkedList<Edge> [] graph = null;
        File f = new File(path);
        BufferedImage image = ImageIO.read(f);
        _robotWidth = robotWidth;

        if(image != null){
            width = image.getWidth();
            height = image.getHeight();
            graph = new LinkedList[height*width];  // graphh is a matrix -> array, matrix[i,j] = graph[i*width + j]
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<Edge>();
            }

            for (int i = 0; i < height - _robotWidth; i++) {
                for (int j = 0; j < width - _robotWidth; j++) {
                    if (image.getRGB(j, i) > _BLACK) {
                        if (i > 0 && image.getRGB(j, i - 1) > _BLACK) { // top pixel
                            if (_enoughSpace(j, i, j, i - 1, image))
                                graph[i * width + j].add(new Edge((i - 1) * width + j, 1));
                        }
                        if (j < width - 1 && i  > 0 && image.getRGB(j + 1, i - 1) > _BLACK) { // top right pixel
                            if (_enoughSpace(j, i, j + 1, i - 1, image))
                                graph[i * width + j].add(new Edge((i - 1) * width + j + 1, Math.sqrt(2)));
                        }
                        if (j < width - 1 && image.getRGB(j + 1, i) > _BLACK) { // right pixel
                            if (_enoughSpace(j, i, j + 1, i, image))
                                graph[i * width + j].add(new Edge(i * width + j + 1, 1));
                        }
                        if (j < width - 1 && i < height - 1 && image.getRGB(j + 1, i + 1) > _BLACK) { // right lower pixel
                            if (_enoughSpace(j, i, j + 1, i + 1, image))
                                graph[i * width + j].add(new Edge((i + 1) * width + j + 1, Math.sqrt(2)));
                        }
                        if (i < height - 1 && image.getRGB(j, i + 1) > _BLACK) { // lower pixel
                            if (_enoughSpace(j, i, j, i + 1, image))
                                graph[i * width + j].add(new Edge((i + 1) * width + j, 1));
                        }
                        if (i < height - 1 && j > 0 && image.getRGB(j - 1, i + 1) > _BLACK) { // lower left pixel
                            if (_enoughSpace(j, i, j - 1, i + 1, image))
                                graph[i * width + j].add(new Edge((i + 1) * width + j - 1, Math.sqrt(2)));
                        }
                        if (j > 0 && image.getRGB(j - 1, i) > _BLACK) { // left pixel
                            if (_enoughSpace(j, i, j - 1, i, image))
                                graph[i * width + j].add(new Edge(i * width + j - 1, 1));
                        }
                        if (j > 0 && i > 0 && image.getRGB(j - 1, i - 1) > _BLACK) { // left top pixel
                            if (_enoughSpace(j, i, j - 1, i - 1, image))
                                graph[i * width + j].add(new Edge((i - 1) * width + j - 1, Math.sqrt(2)));
                        }
                    }

                }
            }
        }
//        for (int i = 0; i < graph.length; i++) {
//            System.out.println(graph[i]);
 //       }
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

    public static Pair<Integer, Integer> findStartFinish(String path) throws IOException {
        File f = new File(path);
        BufferedImage _image = ImageIO.read(f);
        int start = 0, finish = 0;
        // red = -65536

        if(_image != null) {
            int _width = _image.getWidth();
            int _height = _image.getHeight();
            int i = 0; int j = 0; int rgb = 0;

            boolean isFind = false;
            while (i < _height-1 && !isFind) { // find start;
                j=0;
                while ( j < _width-1 && !isFind) {
                    rgb = _image.getRGB(j,i);
                    if( rgb < -1 && rgb > _BLACK ){
                        start = i*_width + j;
                        isFind = true;
                    }
                    j++;
                }
                i++;
            }
            System.out.println(start);
            while (i < _height-1 && j < _width-1 && _image.getRGB(j,i) > _BLACK && _image.getRGB(j,i) < -1){
                i+=3; j+=3;
            }
            isFind = false;
            while (i < _height-1 && !isFind ) { // find finish;
                j=0;
                while (j < _width-1 && !isFind) {
                    rgb = _image.getRGB(j,i);
                    if(rgb < -1 && rgb > _BLACK ){
                        finish = i*_width + j;
                        isFind=true;
                    }
                    j++;
                }
                i++;
            }
            System.out.println(finish);

        }

        return new Pair<Integer, Integer>(start,finish);
    }
}
