package A_star.Algorithm;

/**
 * Created by Konstantin on 10.10.2016.
 */
public class Node {
    Node parent;
    double f, g, h;
    int position;

    public Node(Node parent, double f, double g, double h, int position) {
        this.parent = parent;
        this.f = f;
        this.g = g;
        this.h = h;
        this.position = position;
    }

    public Node(Node parent, double g, double h, int position) {
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.position = position;
        this.f = g+h;
    }

    public double getF() {
        return f;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    public int getPosition() {
        return position;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
