package com.projects.naduni.eventplanner;



import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import static com.projects.naduni.eventplanner.R.*;
import static com.projects.naduni.eventplanner.R.layout.guestsview;


public class GuestsView extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(guestsview, container, false);
        AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();
       animationDrawable.setEnterFadeDuration(2000);
animationDrawable.setExitFadeDuration(4000);
animationDrawable.start();
        getActivity().setTitle("Guests");


        ImageButton guestAdd = (ImageButton)view.findViewById(id.guestadd);


        guestAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new AddGuest());
                ft.commit();


            }});

        return view;


    }


}




