package com.example.mazeroller;

import android.graphics.Canvas;

public class Grid {

    private int width;
    private int height;
    private int horz;
    private int vert;
    private Element[][] elements;

    public Grid(int width, int height, int horz, int vert) {
        this.width = width;
        this.height = height;
        this.horz = horz;
        this.vert = vert;
        elements = new Element[vert][horz];
    }

    public Element[][] getElements() {
        return elements;
    }

    public void setElement(int horz, int vert, Element element) {
        elements[vert][horz] = element;
    }

    public void setGrid(Element[][] elements) {
        this.elements = elements;
    }

    public void erase(Element element) {
        for (int i = 0; i < this.horz; i++) {
            for (int j = 0; j < this.vert; j++) {
                if (elements[j][i] == element) {
                    elements[j][i] = null;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < this.horz; i++) {
            for (int j = 0; j < this.vert; j++) {
                if (elements[j][i] != null) {
                    elements[j][i].draw(canvas,
                            this.width * i / horz,
                            this.height * j / vert,
                            this.width * (i + 1) / horz,
                            this.height * (j + 1) / vert);
                }
            }
        }

    }

}
