package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        ((ProgressBar) findViewById(R.id.h_slider)).setProgress(pref.getInt("hsens", 50));
        ((ProgressBar) findViewById(R.id.v_slider)).setProgress(pref.getInt("vsens", 50));
        ((ProgressBar) findViewById(R.id.sound_slider)).setProgress(pref.getInt("sound", 50));


        ImageButton home = findViewById(R.id.homeSettings);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("sound", ((ProgressBar) findViewById(R.id.sound_slider)).getProgress());
                editor.putInt("hsens", ((ProgressBar) findViewById(R.id.h_slider)).getProgress());
                editor.putInt("vsens", ((ProgressBar) findViewById(R.id.v_slider)).getProgress());
                editor.commit();
                startActivity(new Intent(Settings.this, MainMenu.class));
            }
        });
    }
}
