/**
 * Created by Stanislav on 13.10.2016.
 */
public class Edge {
    private int vNum;
    private double weight;

    public Edge(int vNum, double weight) {
        this.vNum = vNum;
        this.weight = weight;
    }

    public int getvNum() {
        return vNum;
    }

    public void setvNum(int vNum) {
        this.vNum = vNum;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
