package com.projects.naduni.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.projects.naduni.eventplanner.R.*;
import static com.projects.naduni.eventplanner.R.layout.guestsview;


public class GuestsView extends Fragment  {


    private TextView txt_display;
    Spinner spinnerEvent;
    EditText deleteId;
    Button deletebtn, searchbtn;
    SearchView guestSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(guestsview, container, false);
        txt_display = view.findViewById(R.id.txt_display);
       deleteId = view.findViewById(id.editText_deletebudget);
       deletebtn = view.findViewById(id.deleteBudgetButton);
       spinnerEvent = view.findViewById(id.spinner_event_view);
       guestSearch = view.findViewById(id.budgetSearch);
       searchbtn = view.findViewById(id.budget_search);

        final String guestName = guestSearch.toString();
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGuests(guestName);

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               deleteGuest();

           }
       });

        //Background animation
        AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //set title of the page
        getActivity().setTitle("Guests");


        ImageButton guestAdd = (ImageButton)view.findViewById(id.budgetadd);
        guestAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new AddGuest());
                ft.commit();
            }});

        //Spinner Event values
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getEvents(db);

        List<String> spinnerEventValues =new ArrayList<>();
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
            spinnerEventValues.add(name);
        }
        ArrayAdapter<String> Eventadapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,spinnerEventValues);
        spinnerEvent.setAdapter(Eventadapter);
        spinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //call retrieve function
        readGuests();
        return view;
    }



    //delete guests
    public void deleteGuest(){

        DatabaseHelper db = new DatabaseHelper(getActivity());
        SQLiteDatabase mydb = db.getWritableDatabase();

        int id = Integer.parseInt(deleteId.getText().toString());

        if (deleteId == null) {
            Toast.makeText(getActivity(), "Enter valid id", Toast.LENGTH_LONG).show();
        }
        db.deleteGuests(id,mydb);
        db.close();
        deleteId.setText("");
        openGuestFragment();
        Toast.makeText(getActivity(),"Guest removed successfully", Toast.LENGTH_SHORT).show();
    }

    public void openGuestFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new GuestsView());
        ft.commit();
    }


    //retrieve guests from db
    private void readGuests(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor cursor = mydb.getGuests(db);
        String info = " ";
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_NAME));
            String age = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_AGE));
            String gender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_GENDER));
            String status = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_STATUS));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_EMAIL));
            info = info + "\n\n"+"ID : "+id +"\nName : "+ name + "\nAge : "+
                    age + "\nGender :" + gender+"\nStatus : "+status +"\nEmail : "+ email;
        }
        txt_display.setText(info);
        mydb.close();
    }



    //search guests from db
    private void searchGuests(String name){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.searchGuests(name,db);

        String info = " ";

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1));
            String gname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_NAME));
            String age = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_GUEST_AGE));

            info = info + "\n\n"+"ID : "+id +"\nName : "+ gname + "\nAge : "+
                    age ;
        }

        txt_display.setText(info);
        mydb.close();
    }
}




