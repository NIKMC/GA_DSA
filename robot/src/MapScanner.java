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
    public static LinkedList<Integer>[] scan(String path) throws IOException {

        long _BLACK = -16000000;    // black : rgb < -16 000 000
        LinkedList<Integer> [] graph = null;
        File f = new File(path);
        BufferedImage image = ImageIO.read(f);

        if(image != null){
            int width = image.getWidth();
            int height = image.getHeight();
            graph = new LinkedList[height*width];  // graphh is a matrix -> array, matrix[i,j] = graph[i*width + j]
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<Integer>();
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if(image.getRGB(j,i) > _BLACK) {
                        if (i > 0 && image.getRGB(j, i - 1) > _BLACK) { // top pixel
                            graph[i * width + j].add((i - 1) * width + j);
                        }
                        if (i < height - 1 && image.getRGB(j, i + 1) > _BLACK) { // lower pixel
                            graph[i * width + j].add((i + 1) * width + j);
                        }
                        if (j < width - 1 && image.getRGB(j + 1, i) > _BLACK) { // right pixel
                            graph[i * width + j].add(i * width + j + 1);
                        }
                        if (j > 0 && image.getRGB(j - 1, i) > _BLACK) { // left pixel
                            graph[i * width + j].add(i * width + j - 1);
                        }
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
