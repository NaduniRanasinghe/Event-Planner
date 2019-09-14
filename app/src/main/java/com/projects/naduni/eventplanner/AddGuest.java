package com.projects.naduni.eventplanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.naduni.eventplanner.Model.Event;
import com.projects.naduni.eventplanner.Model.Guest;
import com.projects.naduni.eventplanner.Service.DatabaseHelper;

public class AddGuest extends Fragment{

    Button guest_add_button;
    EditText editTextGuestName,editTextAge,editTextNote,editTextEmail;
    Spinner spinnerGender, spinnerEvent;


    DatabaseHelper mydb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_guest, container, false);

        mydb = new DatabaseHelper(getActivity());


        editTextGuestName= (EditText) view.findViewById(R.id.et_guestName);
        guest_add_button = (Button) view.findViewById(R.id.add_guest_button);
        //editTextAge= (EditText) view.findViewById(R.id.et_age);
        editTextNote= (EditText) view.findViewById(R.id.et_notes);
        editTextEmail= (EditText) view.findViewById(R.id.et_email);
        spinnerGender =(Spinner) view.findViewById(R.id.spinner_gender);
        spinnerEvent =(Spinner) view.findViewById(R.id.spinner_event);


        AddGuestData();
        return view;
    }






    public void AddGuestData(){
        guest_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Guest guest = new Guest();
                    guest.setEventName(spinnerEvent.toString());
                    guest.setGuestName(editTextGuestName.getText().toString());
                    guest.setGuestEmail(editTextEmail.getText().toString());
                    guest.setGuestGender(spinnerGender.toString());
                    guest.setAge(editTextAge.getText().toString());
                    guest.setNotesGuest(editTextNote.getText().toString());

                    //Print data in the console
                    System.out.println("Insert Guest Data = " + editTextGuestName.getText().toString() +","+ editTextEmail.getText().toString() +","+  editTextAge.getText().toString() +","+ editTextNote.getText().toString());

                    boolean isInserted = mydb.insertGuestData(guest);
                    if (isInserted = true)
                        Toast.makeText(getActivity(),"Data Inserted Successfully..",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(),"Data Inserted Error ..",Toast.LENGTH_LONG).show();

                    openEventFragment();


                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }

            }
        });
    }

    public void openEventFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new GuestsView());
        ft.commit();
    }


}