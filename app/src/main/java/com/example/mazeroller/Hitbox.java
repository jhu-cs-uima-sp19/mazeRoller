package com.example.mazeroller;

public class Hitbox {

    int left;
    int top;
    int right;
    int bottom;

    public Hitbox(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Hitbox() {
        this.left = this.top = this.right = this.bottom = 0;
    }

    public void setBounds(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean contains(int x, int y) {
        return x > left && x < right && y > top && y < bottom;
    }

}
