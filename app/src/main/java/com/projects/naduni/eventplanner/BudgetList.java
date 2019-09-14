package com.projects.naduni.eventplanner;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import static com.projects.naduni.eventplanner.R.layout.activity_budget_list;


public class BudgetList extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(activity_budget_list, container, false);
        ImageButton add_budget_button = (ImageButton) view.findViewById(R.id.budgetadd);

        getActivity().setTitle("Budget");

        add_budget_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new AddBudget());
                ft.commit();

            }});

        return view;
    }
}
