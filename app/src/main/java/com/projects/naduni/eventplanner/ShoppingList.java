package com.projects.naduni.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.projects.naduni.eventplanner.Model.ShoppingListModel;
import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends Fragment{

    Button shoppinglist_add_button;
    EditText editTextShoppinglistName,editTextNote,editTextPrice, editTextUnits;
    Spinner spinnerPurchased, spinnerQuantityMode,spinnerEvent;


    DatabaseHelper mydb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shopping_list_add, container, false);

        mydb = new DatabaseHelper(getActivity());
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        editTextShoppinglistName= view.findViewById(R.id.editText_shoppingItem);
        shoppinglist_add_button = view.findViewById(R.id.add_shopping_button);
        editTextPrice= view.findViewById(R.id.price);
        editTextNote=  view.findViewById(R.id.notes);
        editTextUnits=  view.findViewById(R.id.units);
        spinnerEvent = view.findViewById(R.id.spinner_eventshopping);
        spinnerPurchased =view.findViewById(R.id.pucrhasedSpinner);
        spinnerQuantityMode =view.findViewById(R.id.quantitymodeSpinner);

        //Spinner Purchased Values
        String[] spinnerPurchasedValues ={"Yes", "No"};
        ArrayAdapter<String> adapterPurchased = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,spinnerPurchasedValues);
        spinnerPurchased.setAdapter(adapterPurchased);
        spinnerPurchased.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner quantity mode values
        String[] spinnerValueHoldValue = {"Per Event", "Per Guest", "Per Age", "Per Gender"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,spinnerValueHoldValue);
        spinnerQuantityMode.setAdapter(adapter);
        spinnerQuantityMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        AddShoppingList();
        return view;
    }

    public void AddShoppingList(){
        shoppinglist_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ShoppingListModel list = new ShoppingListModel();
                    list.setEvent(spinnerEvent.getSelectedItem().toString());
                    list.setItem(editTextShoppinglistName.getText().toString());
                    list.setUnits(editTextUnits.getText().toString());
                    list.setPrice(editTextPrice.getText().toString());
                    list.setPurchased(spinnerPurchased.getSelectedItem().toString());
                    list.setQuantityMode(spinnerQuantityMode.getSelectedItem().toString());
                    list.setNotes(editTextNote.getText().toString());


                    //Print data in the console
                    System.out.println("Insert shopping list Data = " + editTextShoppinglistName.getText().toString());

                    boolean isInserted = mydb.insertShoppingData(list);
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
        ft.replace(R.id.content_frame, new ShoppingListView());
        ft.commit();
    }


}