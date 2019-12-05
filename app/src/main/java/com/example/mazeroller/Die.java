package com.example.mazeroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Die extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_die, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

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
