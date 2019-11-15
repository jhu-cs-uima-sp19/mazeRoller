package com.example.mazeroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.view.View;

public class Stage extends View {

        private static final int CIRCLE_RADIUS = 50; // pixels

        private static Paint mPaint = new Paint();
        private int[] pos = new int[2];
        private float[] vel = new float[2];
        private int viewWidth;
        private int viewHeight;
        private int horz;
        private int vert;

        private Grid grid;

        public Stage(Context context, int width, int height, int horz, int vert) {
            super(context);
            viewWidth = width;
            viewHeight = height;
            this.horz = horz;
            this.vert = vert;
            grid = new Grid(viewWidth, viewHeight, horz, vert);
            mPaint = new Paint();
        }

        public void setElement(int horz, int vert, Element element) {
            grid.setElement(horz, vert, element);
        }

        public void setGrid(Element[][] elements) {
            this.grid.setGrid(elements);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            viewWidth = w;
            viewHeight = h;
        }

        public void onSensorEvent (SensorEvent event) {
            vel[0] = vel[0] + event.values[1] / 2;
            vel[1] = vel[1] + event.values[0] / 2;

            pos[0] = pos[0] + (int) vel[0];
            pos[1] = pos[1] + (int) vel[1];

            enforceBorder();

            Element[][] elems = grid.getElements();

            for (int i = 0; i < this.horz; i++) {
                for (int j = 0; j < this.vert; j++) {
                    if (elems[j][i] instanceof Wall) {
                        enforceWallCollision(elems[j][i]);
                    }
                }
            }

        }

        @Override
        protected void onDraw(Canvas canvas) {
            grid.draw(canvas);
            canvas.drawCircle(pos[0], pos[1], CIRCLE_RADIUS, mPaint);
            invalidate();
        }

        public static void setBallPaint(int color) {
            mPaint.setColor(color);
        }

        private void enforceBorder() {
            if (pos[0] <= 0 + CIRCLE_RADIUS) {
                pos[0] = 0 + CIRCLE_RADIUS;
                vel[0] = 0;
            }
            if (pos[0] >= viewWidth - CIRCLE_RADIUS) {
                pos[0] = viewWidth - CIRCLE_RADIUS;
                vel[0] = 0;
            }
            if (pos[1] <= 0 + CIRCLE_RADIUS) {
                pos[1] = 0 + CIRCLE_RADIUS;
                vel[1] = 0;
            }
            if (pos[1] >= viewHeight - CIRCLE_RADIUS) {
                pos[1] = viewHeight - CIRCLE_RADIUS;
                vel[1] = 0;
            }
        }

        public void enforceWallCollision(Element element) {
            if (element.getHitbox().contains(pos[0] - CIRCLE_RADIUS, pos[1]) && vel[0] < 0) {
                pos[0] = element.getHitbox().right + CIRCLE_RADIUS;
                vel[0] = 0;
            }
            if (element.getHitbox().contains(pos[0] + CIRCLE_RADIUS, pos[1]) && vel[0] > 0) {
                pos[0] = element.getHitbox().left - CIRCLE_RADIUS;
                vel[0] = 0;
            }
            if (element.getHitbox().contains(pos[0], pos[1] - CIRCLE_RADIUS) && vel[1] < 0) {
                pos[1] = element.getHitbox().bottom + CIRCLE_RADIUS;
                vel[1] = 0;
            }
            if (element.getHitbox().contains(pos[0], pos[1] + CIRCLE_RADIUS) && vel[1] > 0) {
                pos[1] = element.getHitbox().top - CIRCLE_RADIUS;
                vel[1] = 0;
            }
        }

}
