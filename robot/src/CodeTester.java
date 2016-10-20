/**
 * Created by NIKMC on 20.10.16.
 */
import A_star.Algorithm.PathFinder;
import General.Point;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import javafx.util.Pair;


import java.io.IOException;
import java.util.LinkedList;

public class CodeTester extends SimpleBenchmark {

    BFS bfs;
    LinkedList<Edge> graph[];
    Pair<Integer, Integer> startFinish;
    Pair<Point, Point> coor;

    @Override
    protected void setUp() throws Exception {

        try {
            startFinish = MapScanner.findStartFinish("Labirint.bmp");
            bfs = new BFS();
            graph = MapScanner.scan("Labirint.bmp", 4);
            bfs.run(graph,startFinish);
            //BFS.DrawTrack("Labirint.bmp",bfs.run(MapScanner.scan("Labirint.bmp", 4),startFinish));
            } catch (IOException e) {
            e.printStackTrace();
        }

        coor = PathFinder.Start("robot/src/lbrnt.bmp");
        super.setUp();

    }

    public static void main(String[] args) throws Exception {
        new Runner().run(CodeTester.class.getName());
    }

    //------------------------------------------------------—

    public void timeSort1(int reps) {
        for (int i = 0; i < reps; i++) {
            // Put your code here
            // something stuff

                bfs.run(graph,startFinish);


            int  g = i*2;
            System.out.println(g);

        }
    }

    public void timeSort2(int reps) {
        for (int i = 0; i < reps; i++) {
            // Put your code here
            // something stuff
            PathFinder.GoToGoal(coor);

            int  g = i*2;
            System.out.println(g);

        }
    }

}
