package com.example.mazeroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Level1 extends Level {

    public Level1() {

        horz = 10;
        vert = 5;
        background = R.color.white;
        startColor = R.color.green;
        ballColor = R.color.blue;
        wallColor = R.color.lightGrey;
        starColor = R.color.yellow;
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
