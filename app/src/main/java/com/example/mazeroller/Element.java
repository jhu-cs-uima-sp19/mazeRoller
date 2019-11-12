package com.example.mazeroller;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Element {

    Paint paint = new Paint();

    void draw(Canvas canvas, int left, int top, int right, int bottom);

}
