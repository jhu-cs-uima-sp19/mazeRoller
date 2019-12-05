package com.example.mazeroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Win extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_win, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        Level level = (Level) getActivity();

        ((TextView) level.findViewById(R.id.time)).setText(
                ((TextView) level.findViewById(R.id.timer)).getText());

        switch (level.stage.stars) {

            case 3:
                ((ImageView) level.findViewById(R.id.collected1)).setImageResource(R.drawable.starfill);
                ((ImageView) level.findViewById(R.id.collected2)).setImageResource(R.drawable.starfill);
                ((ImageView) level.findViewById(R.id.collected3)).setImageResource(R.drawable.starfill);
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .add(R.id.menus, new Record()).addToBackStack("record").commit();
                break;
            case 2:
                ((ImageView) level.findViewById(R.id.collected1)).setImageResource(R.drawable.starfill);
                ((ImageView) level.findViewById(R.id.collected2)).setImageResource(R.drawable.starfill);
                break;
            case 1:
                ((ImageView) level.findViewById(R.id.collected1)).setImageResource(R.drawable.starfill);
                break;
            default:
                break;

        }

        ImageButton home = getActivity().findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainMenu.class));
            }
        });

        ImageButton replay = getActivity().findViewById(R.id.replay);

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if (activity instanceof Level1) {
                    startActivity(new Intent(getActivity(), Level1.class));
                }
                else if (activity instanceof Level2) {
                    startActivity(new Intent(getActivity(), Level2.class));
                }
                else if (activity instanceof Level3) {
                    startActivity(new Intent(getActivity(), Level3.class));
                }
            }
        });
    }

}
