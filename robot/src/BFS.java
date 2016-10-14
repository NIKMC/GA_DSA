import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by NIKMC on 09-Oct-16.
 */
public class BFS {

    private int path = 0;
    private LinkedList<Edg> adj[]; //список смежности
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private Queue<Integer> queue; //очередь для добавления вершин при обходе в ширину

    private void ff(int v){
        if (used[v]) { //если вершина является пройденной, то не производим из нее вызов процедуры
            return;
        }
        queue.add(v); //начинаем обход из вершины v
        used[v] = true; //помечаем вершину как пройденную
        while (!queue.isEmpty()) {
            v = queue.poll(); //извлекаем вершину из очереди
            //System.out.print((v + 1) + " ");
            //запускаем обход из всех вершин, смежных с вершиной v
            path++;
            for (int i = 0; i < adj[v].size(); ++i) {
                int w = adj[v].get(i).vertex.getVertexNumber();
                //если вершина уже была посещена, то пропускаем ее
                if (used[w]) {
                    continue;
                }
                /*if(adj[v].get(i).getPoint().getPath()==0)*/ adj[v].get(i).vertex.setPath(adj[v].get(i).weight+path);
                queue.add(w); //добавляем вершину в очередь обхода
                used[w] = true; //помечаем вершину как пройденную
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

        /*for(int i=0; i<graph.length; i++){
            System.out.print(graph[i].size() + "| " + i + "= Ver | ");
            for(int j=0; j<graph[i].size();j++){
                System.out.print(graph[i].get(j).getvNum() + " ( " + graph[i].get(j).getWeight() + ")" + " - ");
            }
            System.out.println();
        }*/

        adj = editGraph(graph);
        used = new boolean[adj.length];
        queue = new LinkedList<Integer>();
        Arrays.fill(used, false);
        for (int i = 0; i < graph.length; i++) {
            ff(i);
        }

        for(int i=0; i<480; i++){
            System.out.print(adj[i].size() + "| Ver = " + i + "|        ");
            for(int j=0; j<adj[i].size();j++){
                System.out.print("Ycheka " + adj[i].get(j).vertex.getVertexNumber() + " | (path = " + adj[i].get(j).vertex.getPath() + ") ( " + adj[i].get(j).weight + ")" + " - ");
            }
            System.out.println();
        }

    }

    private LinkedList<Edg>[] editGraph(LinkedList<Edge>[] graph){
        LinkedList<Edg> Graph[] = new LinkedList[graph.length];

        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size();j++){
                Graph[i].add(new Edg(new Vertex(graph[i].get(j).getvNum(),0), graph[i].get(j).getWeight()));

            }
        }

return Graph;
    }

public class Edg{
   public double weight;
    public Vertex vertex;
    public Edg (Vertex vertex, double weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}

}
