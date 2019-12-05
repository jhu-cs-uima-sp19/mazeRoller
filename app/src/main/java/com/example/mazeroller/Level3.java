package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Level3 extends Level {

    public Level3() {

        horz = 10;
        vert = 5;
        background = R.color.yellow;
        startColor = R.color.white;
        ballColor = R.color.blue;
        wallColor = R.color.black;
        starColor = R.color.green;
        starBorderColor = R.color.black;
        pitfallColor = R.color.black;
        endColor = R.color.red;

        Element[][] elements = {{new Start(), new Wall(),          null,       null, null,       null,       null,       null, new Star(), new Pitfall()},
                                {       null, new Wall(),    new Star(), new Wall(), null, new Wall(),       null, new Wall(), new Wall(),    new Wall()},
                                {       null, new Wall(), new Pitfall(),       null, null, new Wall(),       null,       null,       null,    new Wall()},
                                {       null, new Wall(),    new Wall(), new Wall(), null, new Wall(), new Wall(), new Wall(),       null,     new End()},
                                { new Star(),       null,          null,       null, null, new Wall(), new Wall(), new Wall(), new Wall(),    new Wall()}};
        this.grid = elements;

    }

}
