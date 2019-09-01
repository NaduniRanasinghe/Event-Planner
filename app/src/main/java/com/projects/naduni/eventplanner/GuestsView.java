package com.projects.naduni.eventplanner;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import static com.projects.naduni.eventplanner.R.*;
import static com.projects.naduni.eventplanner.R.layout.guestsview;


public class GuestsView extends Fragment {

//    String[] guestArray= {"Naduni","Inusha","Pasan","Shaini"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(guestsview, container, false);



//        final Button add_guest_button = (Button) view.findViewById(id.guestAddBtn);
//        add_guest_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AddGuest.class);
//                GuestsView.this.startActivity(intent);
//            }
//        });

//
//
//        ArrayAdapter adapter = new ArrayAdapter<String>();
//
//        ListView listView;
//        listView = view.findViewById(id.guestList);
//        listView.setAdapter(adapter);


        return view;
    }


}




