package com.example.mazeroller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
        public int stars = 0;

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
                    else if (elems[j][i] instanceof Star) {
                        collectStar(elems[j][i]);
                    }
                    else if (elems[j][i] instanceof Pitfall) {
                        die(elems[j][i]);
                    }
                    else if (elems[j][i] instanceof End) {
                        win(elems[j][i]);
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

        private void enforceWallCollision(Element element) {
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

        private void collectStar(Element element) {
            Hitbox hitbox = element.getHitbox();
            if (hitbox.contains(pos[0] - CIRCLE_RADIUS, pos[1]) ||
                    hitbox.contains(pos[0], pos[1] - CIRCLE_RADIUS) ||
                    hitbox.contains(pos[0] + CIRCLE_RADIUS, pos[1]) ||
                    hitbox.contains(pos[0], pos[1] + CIRCLE_RADIUS)) {
                grid.erase(element);
                stars += 1;
                switch (stars) {
                    case 1:
                        ((Activity) getContext()).findViewById(R.id.star1).setBackgroundResource(R.drawable.starfill);
                        break;
                    case 2:
                        ((Activity) getContext()).findViewById(R.id.star2).setBackgroundResource(R.drawable.starfill);
                        break;
                    case 3:
                        ((Activity) getContext()).findViewById(R.id.star3).setBackgroundResource(R.drawable.starfill);
                        break;
                    default:
                        break;
                }
            }
        }

        private void die(Element element) {
            Hitbox hitbox = element.getHitbox();
            if (hitbox.contains(pos[0] - CIRCLE_RADIUS, pos[1]) ||
                    hitbox.contains(pos[0], pos[1] - CIRCLE_RADIUS) ||
                    hitbox.contains(pos[0] + CIRCLE_RADIUS, pos[1]) ||
                    hitbox.contains(pos[0], pos[1] + CIRCLE_RADIUS)) {
                Die die = new Die();
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.menus, die).addToBackStack("die").commit();
            }
        }

        private void win(Element element) {
            Hitbox hitbox = element.getHitbox();
            if (hitbox.contains(pos[0] - CIRCLE_RADIUS, pos[1]) ||
                    hitbox.contains(pos[0], pos[1] - CIRCLE_RADIUS) ||
                    hitbox.contains(pos[0] + CIRCLE_RADIUS, pos[1]) ||
                    hitbox.contains(pos[0], pos[1] + CIRCLE_RADIUS)) {
                Win win = new Win();
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.menus, win).addToBackStack("win").commit();
            }
        }

}
