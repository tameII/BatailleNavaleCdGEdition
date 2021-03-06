package fr.ul.cdg.util;

import fr.ul.cdg.model.strategy.Directions;

import java.io.Serializable;
import java.util.Objects;

public class Vector2 implements Serializable {
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

    public Vector2 adjacent(Directions directions) {
        Vector2 next = new Vector2(this);
        next.add(directions.getAssociatedVector());
        return next;
    }

    public void mult(int m) { this.mult(m,m); }

    public void mult(int x, int y) {this.x *= x ; this.y *= y; }

    public void mult(Vector2 m) {this.mult(m.getX(),m.getY());}

    public void mult(float m) {this.x *= m ; this.y *= m;}

    public String toString(){
        return "x : "+x+", y : "+y;
    }

    public boolean equals(Vector2 obj) { return this.x == obj.getX() && this.y == obj.getY(); }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
