package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class Level extends AppCompatActivity {

    Sensor accelerometer;
    SensorManager manager;
    SensorEventListener listener;
    Stage stage;
    Element[][] grid;
    int horz;
    int vert;
    int background;
    int ballColor;
    int wallColor;
    int starColor;
    int starBorderColor;
    int pitfallColor;
    int endColor;
    long time = SystemClock.elapsedRealtime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ImageButton pause = findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment pauseMenu = new Pause();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.menus, pauseMenu).addToBackStack("pause").commit();
            }
        });

        final Chronometer timer = findViewById(R.id.timer);
        timer.start();

        stage = new Stage(this, (int) MainMenu.screenWidth - 200, (int) MainMenu.screenHeight - 250, horz, vert);
        stage.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        stage.setX(0);
        stage.setY(0);
        stage.setGrid(grid);
        stage.setBackgroundColor(getResources().getColor(background));

        ((ConstraintLayout) findViewById(R.id.stage)).addView(stage);

        Stage.setBallPaint(getResources().getColor(ballColor));
        Wall.setPaintColor(getResources().getColor(wallColor));
        Star.setPaintColor(getResources().getColor(starColor));
        Star.setBorderColor(getResources().getColor(starBorderColor));
        Pitfall.setPaintColor(getResources().getColor(pitfallColor));
        End.setPaintColor(getResources().getColor(endColor));

        manager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                ((TextView) findViewById(R.id.debug)).setText(
                        String.format("%.2f", sensorEvent.values[0]) + " " +
                                String.format("%.2f", sensorEvent.values[1]) + " " +
                                String.format("%.2f", sensorEvent.values[2])
                );
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.menus);
                if (fragment == null) {
                    time = SystemClock.elapsedRealtime() - timer.getBase();
                    timer.start();
                    stage.onSensorEvent(sensorEvent);
                }
                else {
                    timer.setBase(SystemClock.elapsedRealtime() - time);
                    timer.stop();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        };
        manager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

}
