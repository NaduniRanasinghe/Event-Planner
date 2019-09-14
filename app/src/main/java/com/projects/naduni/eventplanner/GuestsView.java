package com.projects.naduni.eventplanner;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.List;

import static android.R.layout.simple_spinner_dropdown_item;
import static com.projects.naduni.eventplanner.R.*;
import static com.projects.naduni.eventplanner.R.layout.guestsview;


public class GuestsView extends Fragment  {


    private TextView txt_display;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(guestsview, container, false);
        txt_display = view.findViewById(R.id.txt_display);
        spinner = view.findViewById(id.spinner_event);

        //spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
       // loadSpinnerData();

        //Baclground animation
        AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();
       animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //set title of the page
        getActivity().setTitle("Guests");


        ImageButton guestAdd = (ImageButton)view.findViewById(id.guestadd);
        guestAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new AddGuest());
                ft.commit();
            }});

        //call retrieve function
        readGuests();

        return view;



    }
/**
    /**
     * Function to load the spinner data from SQLite database

    private void loadSpinnerData() {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<String> events = db.getAllEvents();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, events);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }
        @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


**/
    //retrieve guests from db
    private void readGuests(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getGuests(db);

        String info = " ";

        while(cursor.moveToNext())
        {
            //String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1)));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_NAME));
            String age = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_AGE));
            // String gender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_GENDER));
            info = info + "\n\n"+"Name : "+ name + "\nAge : "+
                    age ;
        }

        txt_display.setText(info);
        mydb.close();
    }
}




