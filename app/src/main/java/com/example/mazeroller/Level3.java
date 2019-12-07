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

        horz = 19;//defines the element size of the grid for number of columns
        vert = 10;//same thing for rows
        background = R.color.red;
        startColor = R.color.white;
        ballColor = R.color.blue;
        wallColor = R.color.darkGrey;
        starColor = R.color.green;
        starBorderColor = R.color.black;
        pitfallColor = R.color.black;
        endColor = R.color.lightGrey;


        Element[][] elements = {
                {new Star(),    new Pitfall(), null,          null,          null,          new Pitfall(), null,          null,          null,          null,          null,       null,          null,          new Pitfall(), null,          null,          null,                null,             new Pitfall()},
                {null,          null,          null,          new Pitfall(), null,          null,          null,          new Wall(),    new Wall(),    new Wall(),    new Wall(), new Pitfall(), null,          null,          null,          new Wall(),    new Wall(),          null,             null},
                {new Pitfall(), new Pitfall(), new Wall(),    new Wall(),    new Wall(),    new Wall(),    new Wall(),    new Pitfall(), null,          new Pitfall(), new Wall(), new Wall(),    new Wall(),    new Wall(),    new Wall(),    new Pitfall(), new Wall(),          new Wall(),       null},
                {new Wall(),    new Wall(),    new Pitfall(), new Pitfall(), new Pitfall(), new Wall(),    new Pitfall(), null,          null,          null,          new Wall(), new Wall(),    new Pitfall(), new Pitfall(), new Pitfall(), null,          new Pitfall(),       new Pitfall(),    null},
                {new Start(),   null,          null,          null,          null,          null,          null,          null,          new Pitfall(), null,          null,       null,          null,          null,          new Pitfall(), null,          null,                null,             null},
                {new Wall(),    new Wall(),    new Pitfall(), new Pitfall(), new Pitfall(), new Wall(),    new Pitfall(), null,          null,          new Wall(),    new Wall(), new Wall(),    new Pitfall(), null,          new Pitfall(), null,          new Pitfall(),       new Pitfall(),    null},
                {new End(),     new Pitfall(), new Wall(),    new Wall(),    new Wall(),    new Pitfall(), new Wall(),    new Pitfall(), new Star(),    new Pitfall(), new Wall(), new Star(),    new Wall(),    null,          null,          null,          new Wall(),          new Wall(),       null},
                {null,          null,          new Wall(),    new Pitfall(), null,          null,          null,          new Wall(),    new Wall(),    new Wall(),    null,       null,          new Wall(),    new Wall(),    new Wall(),    new Wall(),    new Wall(),          null,             null},
                {new Pitfall(), null,          new Pitfall(), new Wall(),    null,          new Pitfall(), null,          new Pitfall(), null,          null,          null,       new Pitfall(), null,          null,          null,          null,          null,                null,             new Pitfall()},
                {null,          null,          null,          null,          null,          new Wall(),    null,          null,          null,          new Pitfall(), null,       null,          null,          new Pitfall(), new Wall(),    new Pitfall(), null,                null,             new Pitfall()}

        };
      this.grid = elements;

    }

}
