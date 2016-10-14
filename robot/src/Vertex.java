/**
 * Created by NIKMC on 14.10.16.
 */
public class Vertex {
    private int vertexNumber;
    private double path;

    public Vertex(int vertexNumber, double path){
        this.vertexNumber = vertexNumber;
        this.path = path;
    }
    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int point) {
        this.vertexNumber = point;
    }

    public double getPath() {
        return path;
    }

    public void setPath(double path) {
        this.path = path;
    }
}
