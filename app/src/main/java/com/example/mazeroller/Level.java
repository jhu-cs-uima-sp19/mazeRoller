package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Level extends AppCompatActivity {

    float screenWidth;
    float screenHeight;

    SensorManager manager;
    Sensor gyroscope;
    SensorEventListener listener;
    BallView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        ball = new BallView(this);
        ball.setBackgroundColor(Color.parseColor("#FFFFFF"));
        ball.setLayoutParams(new ConstraintLayout.LayoutParams((int) screenWidth - 200, (int) screenHeight - 200));
        ball.setX(100);
        ball.setY(100);
        ((ConstraintLayout) findViewById(R.id.level)).addView(ball);

        manager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        gyroscope = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                ((TextView) findViewById(R.id.debug)).setText(
                        String.format("%.2f", sensorEvent.values[0]) + " " +
                                String.format("%.2f", sensorEvent.values[1]) + " " +
                                String.format("%.2f", sensorEvent.values[2])
                );
                ball.onSensorEvent(sensorEvent);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        manager.registerListener(listener, gyroscope, SensorManager.SENSOR_DELAY_GAME);
    }

    public class BallView extends View {

        private static final int CIRCLE_RADIUS = 50; //pixels

        private Paint mPaint;
        private int[] pos = new int[2];
        private float[] vel = new float[2];
        private int viewWidth;
        private int viewHeight;

        public BallView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(getResources().getColor(R.color.blue));
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

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(pos[0], pos[1], CIRCLE_RADIUS, mPaint);
            invalidate();
        }
    }

}
