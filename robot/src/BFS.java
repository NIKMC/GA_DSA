import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by NIKMC on 09-Oct-16.
 */
public class BFS {

    private int path = 0;
    private LinkedList<Edge> adj[]; //список смежности
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private Queue<Integer> queue; //очередь для добавления вершин при обходе в ширину
    private double ver[];
    private List<Integer> shortPath = new ArrayList<>();
    private void ff(int v){
        if (used[v]) { //если вершина является пройденной, то не производим из нее вызов процедуры
            return;
        }
        queue.add(v); //начинаем обход из вершины v
        used[v] = true; //помечаем вершину как пройденную
        //ver[v] = (path);
        while (!queue.isEmpty()) {
            v = queue.poll(); //извлекаем вершину из очереди
            //path++;
            //System.out.print((v + 1) + " ");
            //запускаем обход из всех вершин, смежных с вершиной v
            for (int i = 0; i < adj[v].size(); ++i) {
                int w = adj[v].get(i).getvNum();
                //если вершина уже была посещена, то пропускаем ее
                if (used[w]) {
                    continue;
                }

                queue.add(w); //добавляем вершину в очередь обхода
                used[w] = true; //помечаем вершину как пройденную
                ver[w] = (adj[v].get(i).getWeight()+ver[v]);
            }

        }

//            adj[v].get(i).setPoint(path);

    }
    //процедура обхода в ширину
    /*private void bfs(int v) {
        if (used[v]) { //если вершина является пройденной, то не производим из нее вызов процедуры
            return;
        }
        queue.add(v); //начинаем обход из вершины v
        used[v] = true; //помечаем вершину как пройденную
        while (!queue.isEmpty()) {
            v = queue.poll(); //извлекаем вершину из очереди
            System.out.print((v + 1) + " ");
            //запускаем обход из всех вершин, смежных с вершиной v
            for (int i = 0; i < adj[v].size(); ++i) {
                int w = adj[v].get(i).getvNum();
                //если вершина уже была посещена, то пропускаем ее
                if (used[w]) {
                    continue;
                }
                queue.add(w); //добавляем вершину в очередь обхода
                used[w] = true; //помечаем вершину как пройденную
            }
        }
    }*//*
    public void run(LinkedList<Integer>[] array) {
        adj = array;
        used = new boolean[array.length];
        Arrays.fill(used, false);
        for (int i = 0; i < array.length; ++i) {
            bfs(i);
        }
    }*/
    public void run(LinkedList<Edge>[] graph) {

        /*for(int i=0; i<1000; i++){
            System.out.print(graph[i].size() + "| " + i + "= Ver | ");
            for(int j=0; j<graph[i].size();j++){
                System.out.print(graph[i].get(j).getvNum() + " ( " + graph[i].get(j).getWeight() + ")" + " - ");
            }
            System.out.println();
        }*/
        adj = graph;
        ver = new double[adj.length];

        used = new boolean[adj.length];
        queue = new LinkedList<Integer>();
        Arrays.fill(used, false);
        for (int i = 0; i < graph.length-(480*4); i++) {
            ff(i);
        }

/*for(int i=0;i<ver.length;i++){
    System.out.println("ver = " + i + "| path = " + ver[i]);
}*/
        for(int i=0; i<1000; i++){
            System.out.print(adj[i].size() + "| Ver = " + i + "|        ");
            for(int j=0; j<adj[i].size();j++){
                System.out.print("Ycheka " + adj[i].get(j).getvNum() + " | (path = " + ver[adj[i].get(j).getvNum()] + ") ( " + adj[i].get(j).getWeight() + ")" + " - ");
            }
            System.out.println();
        }
        createPath();
        /*for(int i=0; i<adj.length; i++){
            System.out.print(adj[i].size() + "| Ver = " + i + "|        ");
            for(int j=0; j<adj[i].size();j++){
                System.out.print("Ycheka " + adj[i].get(j).getvNum() + " | (path = " + ver[adj[i].get(j).getvNum()] + ") ( " + adj[i].get(j).getWeight() + ")" + " - ");
            }
            System.out.println();
        }*/
        for(int i=0;i<shortPath.size(); i++){
            System.out.println("Ycheka = " + shortPath.get(i));
        }
        DrawTrack("Labirint.bmp",shortPath);

    }

    private void createPath() {
        if (ver[227995] != 0) {
            double path = ver[227995];
//            do{
            for (int i = 227995; i >= 0; i--) {
                if (ver[i] == path) {
                    shortPath.add(i);
                    path-= ver[i];
                }
            }
//            } while (ver[0] == path);
        if(shortPath.contains(0) ){
            System.out.println("путь найден");
        } else {
            System.out.println("путь не найден");
        }
    }
       /* перейти в финишную ячейку
        ЦИКЛ
        выбрать среди соседних ячейку, помеченную числом на 1 меньше числа в текущей ячейке
        перейти в выбранную ячейку и добавить её к пути
        ПОКА текущая ячейка — не стартовая
        ВОЗВРАТ путь найден
                ИНАЧЕ*/

    }

    /*private static void DrawTrack(String path, List<Integer> shortPath){
        File f = new File(path);
        int pixelColor = 11236;
        try {
            BufferedImage image = ImageIO.read(f);
            for(int i=0;i<shortPath.size(); i++){
                if(shortPath.get(i)==0){
                    image.setRGB(shortPath.get(i), shortPath.get(i), pixelColor);
                }else{
                    image.setRGB(shortPath.get(i)%480, shortPath.get(i)/480, pixelColor);
                }
            }
            image.setRGB(0,0, pixelColor);
            ImageIO.write(image, "bmp", f);
        } catch (IOException e) {

        }
    }*/
    private static void DrawTrack(String path, List<Integer> shortPath){
        File f = new File(path);
        int pixelColor = new Color(255,0,0).getRGB();
        try {
            BufferedImage image = ImageIO.read(f);

            for(int i=0;i<shortPath.size(); i++){
                if(shortPath.get(i)==0){
                    image.setRGB(shortPath.get(i), shortPath.get(i), pixelColor);
                }else{
                    image.setRGB(shortPath.get(i)%480, shortPath.get(i)/480, pixelColor);
                }
            }
            image.setRGB(2,2, pixelColor);
            ImageIO.write(image, "bmp", f);
        } catch (IOException e) {

        }
    }

    private LinkedList<Edg>[] editGraph(LinkedList<Edge>[] graph){
        LinkedList<Edg> Graph[] = new LinkedList[graph.length];

/*        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size();j++){
                Graph[i].add(new Edg(
                        new Vertex(graph[i].get(j).getvNum(),0), (int)graph[i].get(j).getWeight()));

            }
        }*/

return Graph;
    }

public class Edg{
   public int weight;
    public Vertex vertex;
    public Edg (Vertex vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}

}
