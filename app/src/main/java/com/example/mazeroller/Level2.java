package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Level2 extends Level {

    public Level2() {

        horz = 10;
        vert = 5;
        background = R.color.green;
        startColor = R.color.lightGrey;
        ballColor = R.color.blue;
        wallColor = R.color.darkGrey;
        starColor = R.color.yellow;
        starBorderColor = R.color.black;
        pitfallColor = R.color.black;
        endColor = R.color.red;

        Element[][] elements = {{ new Star(), new Wall(),          null, new Star(), new Pitfall(), new Wall(),       null,          null, new Star(), new Pitfall()},
                                {       null, new Wall(),          null, new Wall(),          null,       null,       null,    new Wall(), new Wall(),    new Wall()},
                                {       null, new Wall(),          null,       null,          null, new Wall(),       null,          null,       null,    new Wall()},
                                {       null, new Wall(),    new Wall(), new Wall(),          null, new Wall(), new Wall(), new Pitfall(),       null,     new End()},
                                {       null,       null,          null,       null,   new Start(), new Wall(), new Wall(),    new Wall(), new Wall(),    new Wall()}};
        this.grid = elements;

    }

}
