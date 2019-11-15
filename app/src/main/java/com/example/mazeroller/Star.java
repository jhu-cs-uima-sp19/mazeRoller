package com.example.mazeroller;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Star implements Element {

    static Paint paint = new Paint();
    Hitbox hitbox = new Hitbox();

    @Override
    public void draw(Canvas canvas, int left, int top, int right, int bottom) {
        Path path = new Path();
        paint.setStyle(Paint.Style.FILL);
        float[] mid = {(right + left) / 2, (bottom + top) / 2};
        float rad = (float) (Math.min(right - left, bottom - top) / 3);
        // top left
        path.moveTo(mid[0] + rad * (float) Math.sin(Math.toRadians(288)), mid[1] - rad * (float) Math.cos(Math.toRadians(288)));
        // top right
        path.lineTo(mid[0] + rad * (float) Math.sin(Math.toRadians(72)), mid[1] - rad * (float) Math.cos(Math.toRadians(72)));
        // bottom left
        path.lineTo(mid[0] + rad * (float) Math.sin(Math.toRadians(216)), mid[1] - rad * (float) Math.cos(Math.toRadians(216)));
        // top tip
        path.lineTo(mid[0] + rad * (float) Math.sin(Math.toRadians(0)), mid[1] - rad * (float) Math.cos(Math.toRadians(0)));
        // bottom right
        path.lineTo(mid[0] + rad * (float) Math.sin(Math.toRadians(144)), mid[1] - rad * (float) Math.cos(Math.toRadians(144)));
        // top left
        path.lineTo(mid[0] + rad * (float) Math.sin(Math.toRadians(288)), mid[1] - rad * (float) Math.cos(Math.toRadians(288)));

        path.close();
        canvas.drawPath(path, paint);
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
