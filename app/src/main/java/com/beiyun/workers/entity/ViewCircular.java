package com.beiyun.workers.entity;

public class ViewCircular {

    private int cx;
    private int cy;
    private int dx;
    private int dy;
    private float finalRadius;

    public ViewCircular() {
    }

    public ViewCircular(int cx, int cy, float finalRadius) {
        this.cx = cx;
        this.cy = cy;
        this.finalRadius = finalRadius;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public float getFinalRadius() {
        return finalRadius;
    }

    public void setFinalRadius(float finalRadius) {
        this.finalRadius = finalRadius;
    }
}
