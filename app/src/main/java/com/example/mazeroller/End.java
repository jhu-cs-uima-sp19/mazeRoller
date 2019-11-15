package com.example.mazeroller;

import android.graphics.Canvas;
import android.graphics.Paint;

public class End implements Element {

    static Paint paint = new Paint();
    Hitbox hitbox = new Hitbox();

    @Override
    public void draw(Canvas canvas, int left, int top, int right, int bottom) {
        canvas.drawRect(left, top, right, bottom, paint);
        setHitbox(left, top, right, bottom);
    }

    @Override
    public void setHitbox(int left, int top, int right, int bottom) {
        hitbox.setBounds(left, top, right, bottom);
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public static void setPaintColor(int color) {
        paint.setColor(color);
    }

}
