package com.example.mazeroller;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Star implements Element {

    static Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas, int left, int top, int right, int bottom) {
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public static void setPaintColor(int color) {
        paint.setColor(color);
    }

}