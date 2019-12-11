package fr.ul.cdg.util;

public class Vector2 {
    private int x, y;

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 copy){
        this.x=copy.getX();
        this.y=copy.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Vector2 delta) { this.x += delta.getX() ; this.y += delta.getY(); }

    public void add(int x, int y) { this.add(new Vector2(x,y)); }

    public void mult(int m) { this.mult(m,m); }

    public void mult(int x, int y) {this.x *= x ; this.y *= y; }

    public void mult(Vector2 m) {this.mult(m.getX(),m.getY());}

    public void mult(float m) {this.x *= m ; this.y *= m;}

    public String toString(){
        return "x : "+x+", y : "+y;
    }
}
