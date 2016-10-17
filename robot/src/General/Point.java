package General;

/**
 * Created by Konstantin on 10.10.2016.
 */
public class Point {
    int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean PositionEquals(Point position) {
        if (this.x == position.getX() && this.y == position.getY())
            return true;
        else
            return false;
    }
}
