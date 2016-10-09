import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by NIKMC on 09-Oct-16.
 */
public class BFS {

    private LinkedList<Integer> adj[]; //список смежности
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах
    private Queue<Integer> queue; //очередь для добавления вершин при обходе в ширину

    //процедура обхода в ширину
    private void bfs(int v) {
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
                int w = adj[v].get(i);
                //если вершина уже была посещена, то пропускаем ее
                if (used[w]) {
                    continue;
                }
                queue.add(w); //добавляем вершину в очередь обхода
                used[w] = true; //помечаем вершину как пройденную
            }
        }
    }
    public void run(LinkedList<Integer>[] array) {
        adj = array;
        used = new boolean[array.length];
        Arrays.fill(used, false);
        for (int i = 0; i < array.length; ++i) {
            bfs(i);
        }
    }
}
