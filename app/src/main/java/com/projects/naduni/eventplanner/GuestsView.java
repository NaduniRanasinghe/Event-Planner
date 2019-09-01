package com.projects.naduni.eventplanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



import static com.projects.naduni.eventplanner.R.*;
import static com.projects.naduni.eventplanner.R.layout.guestsview;


public class GuestsView extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(guestsview, container, false);


        getActivity().setTitle("Guests");

        Button guestAdd = (Button)view.findViewById(id.guestadd);


        guestAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new AddGuest());
                ft.commit();


            }});
//        final Button creat_event_button = (Button) view.findViewById(id.guestadd);
//        creat_event_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AddGuest.class);
//                GuestsView.this.startActivity(intent);
//            }
//        });

        return view;


    }


}




