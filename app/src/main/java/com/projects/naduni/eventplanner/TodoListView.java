package com.projects.naduni.eventplanner;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;


public class TodoListView extends Fragment {


    Button btn_viewTodos;
    DatabaseHelper mydb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("To-Do List");
        View view = inflater.inflate(R.layout.todolist_view, container, false);
        btn_viewTodos = (Button)view.findViewById(R.id.addTodoListbutton);
        mydb = new DatabaseHelper(getActivity());

        btn_viewTodos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new TodoListAdd());
                ft.commit();

            }});
        return view;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}