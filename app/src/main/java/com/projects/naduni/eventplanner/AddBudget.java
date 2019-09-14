package com.projects.naduni.eventplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

public class AddBudget extends Fragment {

    DatabaseHelper myDb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myDb = new DatabaseHelper(getActivity());

        //Set title
        getActivity().setTitle("Budget");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_budget, container, false);

        return view;
    }

}
