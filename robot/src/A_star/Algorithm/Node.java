package A_star.Algorithm;

import General.Point;

/**
 * Created by Konstantin on 10.10.2016.
 */
public class Node {
    Node parent;
    double f, g, h;
    Point position;

    public int[] getLinks() {
        return links;
    }

    int [] links = new int[8];

    public Node(Node parent, double f, double g, double h, Point position) {
        this.parent = parent;
        this.f = f;
        this.g = g;
        this.h = h;
        this.position = position;
    }

    public Node() {
    }

    public Node(Node parent, double g, Point position, Point targetPosition) {
        this.parent = parent;
        this.g = g;
        this.position = position;
        this.f = g + h;

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

    public Point getPosition() {
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
        this.f  = g + this.h;
    }

    public void setG(Node parent) {
        if(parent.getPosition().getX() == this.position.getX() || parent.getPosition().getY() == this.position.getY())
            this.g = parent.getG() + 1;
        else
            this.g = parent.getG() + Math.sqrt(2.0);
        this.f  = g + this.h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void CalculateH(Point targetPosition){
//        this.h = Math.sqrt(Math.pow((targetPosition.getX() - this.position.getX()),2) + Math.pow((targetPosition.getY() - this.position.getY()),2));
        this.h = targetPosition.getX() - this.position.getX() + targetPosition.getY() - this.position.getY();
    }

    public void AddLink(int index, int value){
        this.links[index] = value;
    }



}
