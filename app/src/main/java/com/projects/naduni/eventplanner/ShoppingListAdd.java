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
import android.widget.Toast;

import com.projects.naduni.eventplanner.Model.Todo;

public class ShoppingListAdd extends Fragment {

    Button shoppinglistAdd1;
    EditText edittext2;
    EditText edittext3;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Shopping-List-Add");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_shopping_list_add, container, false);
        shoppinglistAdd1 = (Button)view.findViewById(R.id.shoppinglistAdd);
        edittext2 =(EditText)view.findViewById(R.id.editText2);
        edittext3 =(EditText)view.findViewById(R.id.editText4);

        shoppinglistAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (edittext2.getText().toString().matches("") && edittext3.getText().toString().matches("")) {
                        Toast.makeText(getActivity(),"Data Inserted Error! Please fill the data ...",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "Data Inserted Successfully..", Toast.LENGTH_LONG).show();
                        openEventFragment();

                    }

                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }});
        return view;
    }


    public void openEventFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new ShoppingListView());
        ft.commit();
    }

}
