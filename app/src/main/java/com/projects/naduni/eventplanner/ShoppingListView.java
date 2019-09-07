package com.projects.naduni.eventplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ShoppingListView extends Fragment {


    Button btn_addshopinglist;

    Button btn_editItems;

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

        btn_addshopinglist =(Button)view.findViewById(R.id.shopping_list_add);
        btn_editItems = (Button)view.findViewById(R.id.edit_item);

        btn_addshopinglist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new ShoppingListAdd());
                ft.commit();

            }});

        btn_editItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new ShoppingListUpdate());
                ft.commit();

            }});

        return view;
    }

}
