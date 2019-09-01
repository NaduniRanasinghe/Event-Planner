package com.projects.naduni.eventplanner;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

public class GuestsView extends Fragment {



//    Button btn_viewTodos;
//    DatabaseHelper mydb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guestsview, container, false);

        return view;
    }

//    public void showMessage(String title, String message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//
//    }
}




