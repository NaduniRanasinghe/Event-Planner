package com.projects.naduni.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListView extends Fragment {


    ImageButton btn_addshopinglist;
    Spinner shoppingevents;
    Button shoppinglistButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Shopping-List-View");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_shopping_list_view, container, false);

        btn_addshopinglist =view.findViewById(R.id.shoppingadd);
        shoppingevents = view.findViewById(R.id.spinner_event_shopping_list);
        shoppinglistButton = (Button)view.findViewById(R.id.edit_shopping_button);

        btn_addshopinglist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new ShoppingListAdd());
                ft.commit();

            }});
        shoppinglistButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new ShoppingListUpdate());
                ft.commit();

            }});


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
        shoppingevents.setAdapter(Eventadapter);
        shoppingevents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

}
