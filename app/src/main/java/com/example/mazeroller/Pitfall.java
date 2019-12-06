package com.example.mazeroller;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Pitfall implements Element {

    static Paint paint = new Paint();
    Hitbox hitbox = new Hitbox();

    @Override
    public void draw(Canvas canvas, int left, int top, int right, int bottom) {
        canvas.drawCircle((left + right) / 2, (top + bottom) / 2, Math.min((right - left), (bottom - top)) / 2.5f, paint);
        setHitbox((left + right) / 2 - (int) (Math.min((right - left), (bottom - top)) / 2.5f),
                (top + bottom) / 2 - (int) (Math.min((right - left), (bottom - top)) / 2.5f),
                (left + right) / 2 + (int) (Math.min((right - left), (bottom - top)) / 2.5f),
                (top + bottom) / 2 + (int) (Math.min((right - left), (bottom - top)) / 2.5f));
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
