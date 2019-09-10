package com.projects.naduni.eventplanner;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.projects.naduni.eventplanner.R.layout.activity_budget_list;


public class BudgetList extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(activity_budget_list, container, false);


        getActivity().setTitle("Budget");

        return view;
    }
}
