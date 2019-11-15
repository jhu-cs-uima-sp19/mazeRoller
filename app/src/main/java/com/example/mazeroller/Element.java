package com.example.mazeroller;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Element {

    void draw(Canvas canvas, int left, int top, int right, int bottom);

    void setHitbox(int left, int top, int right, int bottom);
    Hitbox getHitbox();

}
