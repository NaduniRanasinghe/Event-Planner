package com.projects.naduni.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListView extends Fragment {


    ImageButton btn_addshopinglist;
    Spinner shoppingevents;
    TextView txt_display;
    EditText deleteId;
    Button deletebtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Shopping List");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_shopping_list_view, container, false);

        btn_addshopinglist =view.findViewById(R.id.shoppingadd);
        shoppingevents = view.findViewById(R.id.spinner_event_shopping_list);
        txt_display = view.findViewById(R.id.shopping_display);
        deleteId = view.findViewById(R.id.editText_deleteShopping);
        deletebtn = view.findViewById(R.id.deleteShoppingButton);



        //set onclicklistener to add button
        btn_addshopinglist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new ShoppingList());
                ft.commit();

            }});



        //set onclicklistener to DELETE button
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();

            }
        });


        //get spinner values
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
        readShoppingItems();
        return view;
    }


    //delete items
    public void deleteItem(){

        DatabaseHelper db = new DatabaseHelper(getActivity());
        SQLiteDatabase mydb = db.getWritableDatabase();

        int id = Integer.parseInt(deleteId.getText().toString());

        if (deleteId == null) {
            Toast.makeText(getActivity(), "Enter valid id", Toast.LENGTH_LONG).show();
        }


        db.deleteShoppingItem(id,mydb);

        db.close();
        deleteId.setText("");
        openGuestFragment();
        Toast.makeText(getActivity(),"Item removed successfully", Toast.LENGTH_SHORT).show();

    }

    public void openGuestFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new ShoppingListView());
        ft.commit();
    }


    //retrieve shopping items from db
    private void readShoppingItems(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getShoppingItems(db);

        String info = " ";

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_SHOPPING_ITEM));
            String event = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_SHOPPING_EVENT));
            String purchased = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_SHOPPING_PURCHASED));
            String price = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PRICE));
            String units = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_UNITS));
            String quantityMode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_QUANTITYMODE));
            String notes = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTES));


            info = info + "\n\n"+"ID : "+id +"\nName : "+ name + "\nEvent : "+
                    event + "\nIs Purchased? :" + purchased+"\nPrice: "+price +"\nUnits : "+ units + "\nQuantity Mode: "+ quantityMode +
            "\nNotes: "+ notes;


        }

        txt_display.setText(info);

        mydb.close();
    }


}
