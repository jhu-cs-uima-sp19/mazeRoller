package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Level extends AppCompatActivity {

    float screenWidth;
    float screenHeight;

    SensorManager manager;
    Sensor gyroscope;
    SensorEventListener listener;
    Stage stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        stage = new Stage(this, (int) screenWidth - 200, (int) screenHeight - 200, 10, 5);
        stage.setBackgroundColor(Color.parseColor("#FFFFFF"));
        stage.setLayoutParams(new ConstraintLayout.LayoutParams((int) screenWidth - 200, (int) screenHeight - 200));
        stage.setX(100);
        stage.setY(100);
        ((ConstraintLayout) findViewById(R.id.level)).addView(stage);

        Element[][] elements = {{null, new Wall(),       null,       null,       null,       null,       null, new Wall(), new Wall(), new Wall()},
                                {null, new Wall(),       null, new Wall(), new Wall(), new Wall(),       null,       null, new Wall(), new Wall()},
                                {null, new Wall(),       null,       null,       null, new Wall(), new Wall(),       null,       null, new Wall()},
                                {null, new Wall(), new Wall(), new Wall(),       null, new Wall(), new Wall(), new Wall(),       null,       null},
                                {null,       null,       null,       null,       null, new Wall(), new Wall(), new Wall(), new Wall(), new Wall()}};

        stage.setGrid(elements);

        Stage.setBallPaint(getResources().getColor(R.color.blue));
        Wall.setPaintColor(getResources().getColor(R.color.darkGrey));

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
                stage.onSensorEvent(sensorEvent);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        manager.registerListener(listener, gyroscope, SensorManager.SENSOR_DELAY_GAME);
    }

}
