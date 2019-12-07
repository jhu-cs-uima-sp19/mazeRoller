package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        ((SeekBar) findViewById(R.id.h_slider)).setProgress(pref.getInt("hsens", 50));
        ((SeekBar) findViewById(R.id.v_slider)).setProgress(pref.getInt("vsens", 50));
        ((SeekBar) findViewById(R.id.s_slider)).setProgress(pref.getInt("sound", 50));

        ImageButton home = findViewById(R.id.homeSettings);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("sound", ((SeekBar) findViewById(R.id.s_slider)).getProgress());
                editor.putInt("hsens", ((SeekBar) findViewById(R.id.h_slider)).getProgress());
                editor.putInt("vsens", ((SeekBar) findViewById(R.id.v_slider)).getProgress());
                editor.commit();
                startActivity(new Intent(Settings.this, MainMenu.class));
            }
        });
    }
}
