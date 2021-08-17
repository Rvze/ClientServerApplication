package general;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 5545L;
    private long x;
    private Integer y; //Поле не может быть null

    public Coordinates(long x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {

    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
