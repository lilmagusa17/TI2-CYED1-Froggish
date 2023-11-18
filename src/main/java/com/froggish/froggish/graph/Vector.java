package com.froggish.froggish.graph;

public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(Vector v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void div(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void normalize() {
        double m = this.mag();
        if (m != 0) {
            this.div(m);
        }
    }


}
