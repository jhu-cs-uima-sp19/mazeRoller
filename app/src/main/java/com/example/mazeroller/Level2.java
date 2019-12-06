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

        horz = 20;
        vert = 10;
        background = R.color.green;
        startColor = R.color.lightGrey;
        ballColor = R.color.blue;
        wallColor = R.color.darkGrey;
        starColor = R.color.yellow;
        starBorderColor = R.color.black;
        pitfallColor = R.color.black;
        endColor = R.color.red;

        Element[][] elements = {
                { new Wall(),    new Wall(),    new Wall(),  new Wall(),  new Wall(), new Wall(),  new Wall(),  new Wall(),  new Wall(),   new Wall(),    new Wall(),      new Wall(),  new Wall(), new Wall(), new Wall(), new Wall(),  new Wall(), new Wall(), new Wall(), new Wall()},
                {       null, new Pitfall(),          null,        null,        null,       null,  new Star(),  new Wall(),  new Star(),         null,          null,   new Pitfall(),  new Wall(), new Wall(), new Wall(), new Wall(),  new Wall(),       null, new End(),  new Wall()},
                {       null,          null,          null,        null,        null,       null,        null,  new Wall(),        null,         null,          null,            null,  new Wall(), new Wall(), new Pitfall(),    null,        null,       null,      null,  new Wall()},
                {       null,          null,    new Wall(),  new Wall(),  new Wall(),       null,        null,  new Wall(),  new Wall(),   new Wall(), new Pitfall(),            null,  new Wall(), new Wall(),          null,    null,        null,    null, new Pitfall(),  new Wall()},
                { new Pitfall(),       null,    new Wall(),  new Wall(),  new Wall(), new Pitfall(),     null,  new Wall(),  new Wall(),   new Wall(),          null,            null,  new Pitfall(), new Wall(),       null,    null,  new Wall(),    new Wall(), new Wall(),  new Wall()},
                {          null,       null,    new Wall(),  new Wall(),  new Wall(),          null,     null,  new Pitfall(),  new Wall(),   new Wall(),          null,   new Wall(),     new Wall(), new Wall(),       null,    null,  new Wall(),    new Wall(), new Wall(),  new Wall()},
                {          null,new Pitfall(),  new Wall(), new Start(),        null,          null, new Wall(), new Wall(),    new Wall(),   new Wall(),          null,   new Wall(),           null,       null,       null, new Pitfall(),  new Wall(),    new Wall(), new Wall(),  new Wall()},
                {          null,         null,  new Wall(),        null,        null,          null,       null,       null,          null,   new Pitfall(),       null, new Pitfall(),           null,       null,new Wall(),    new Wall(),  new Wall(),    new Pitfall(), new Pitfall(),  new Pitfall()},
                {          null,         null,        null,        null,        null,          null,       null,       null,          null,            null,       null,          null,           null,       null,new Wall(),    new Wall(),  new Wall(),    null, new Star(),  new Pitfall()},
                { new Pitfall(),       null,          null,       null,   new Wall(), new Wall(), new Wall(),    new Wall(), new Wall(),    new Wall(), new Wall(), new Wall(),          null, null, null, null,       null,          null, new Pitfall(), new Pitfall()},


        };
        this.grid = elements;

    }

}
