package com.projects.naduni.eventplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.projects.naduni.eventplanner.Model.Event;
import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.Calendar;


/**
 * Created by inushaV on 8/31/2019.
 */

public class CreateEvent extends Fragment implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Button date_pick_button,event_add_button;
    TextView date_view;
    EditText editTextEventName,editTextLocation,editTextNote,editTextDate;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    DatabaseHelper myDb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myDb = new DatabaseHelper(getActivity());

        //Set title
        getActivity().setTitle("Events");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_event, container, false);

        date_pick_button = (Button) view.findViewById(R.id.button_pick_date);
        event_add_button = (Button) view.findViewById(R.id.eventAddButton);
        date_view = (TextView) view.findViewById(R.id.edit_event_date);

        editTextEventName = (EditText) view.findViewById(R.id.edit_event_name);
        editTextLocation = (EditText) view.findViewById(R.id.edit_event_location);
        editTextNote = (EditText) view.findViewById(R.id.edit_event_note);


        date_pick_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), CreateEvent.this,
                        year, month, day);
                datePickerDialog.show();
            }
        });

        //insert data to the Event table
        AddEventData();

        return view;
    }

    public void AddEventData(){
        event_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                Event event = new Event();
                event.setEventName(editTextEventName.getText().toString());
                event.setLocation(editTextLocation.getText().toString());
                event.setDate(date_view.getText().toString());
                event.setNote(editTextNote.getText().toString());

                //Print data in the console
                System.out.println("Insert Event Data = " + editTextEventName.getText().toString() +","+ editTextLocation.getText().toString() +","+  date_view.getText().toString() +","+ editTextNote.getText().toString());

                boolean isInserted = myDb.insertEventData(event);
                if (isInserted = true)
                    Toast.makeText(getActivity(),"Data Inserted Successfully..",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(),"Data Inserted Error ..",Toast.LENGTH_LONG).show();

                openEventFregment();


            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }

            }
        });
    }

    public void openEventFregment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new EventFragment());
        ft.commit();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), CreateEvent.this,
                hour, minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
         hourFinal = i;
         minuteFinal = i1;

        date_view.setText(dayFinal +"/"+ monthFinal +"/"+ yearFinal +" "+ hourFinal +":"+ minute);
    }
}
