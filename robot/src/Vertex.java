/**
 * Created by NIKMC on 14.10.16.
 */
public class Vertex {
    public int vertexNumber;
    public int path;

    public Vertex(int vertexNumber, int path){
        this.vertexNumber = vertexNumber;
        this.path = path;
    }
    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int point) {
        this.vertexNumber = point;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
