package com.projects.naduni.eventplanner;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;


public class TodoListView extends Fragment {

    DatabaseHelper mydb;

    TextView todolistview;
    Spinner shoppingevents;
    EditText deleteId;
    Button addTodoList,deletebtn,editbtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("To-Do List");
        final View view = inflater.inflate(R.layout.todolist_view, container, false);
        todolistview = view.findViewById(R.id.todolist_display);
        addTodoList = view.findViewById(R.id.button_addTodos);
        deleteId = view.findViewById(R.id.editText_deleteshoppinglist);
        deletebtn = view.findViewById(R.id.deleteEventButton);
        editbtn = view.findViewById(R.id.edit_todo_button);

        mydb = new DatabaseHelper(getActivity());

        addTodoList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new TodoListAdd());
                ft.commit();

            }});

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();

            }
        });

        editbtn.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view1) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mview = getLayoutInflater().inflate(R.layout.todolist_update_dialog, null);
                EditText notes = (EditText) mview.findViewById(R.id.editnotes);
                EditText tasks = (EditText) mview.findViewById(R.id.edittasks);
                Button mEdit = (Button) mview.findViewById(R.id.editbutton);

                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });
        readTodoListItems();
        return view;


    }

    //show the todolist items
    private void readTodoListItems(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getTodoListData(db);

        String info = " ";

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1));
            String todotask = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TASKS));
            String todonote = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTES));
            info = info + "\n\n"+"ID : "+id +"\nTask : "+ todotask + "\nNote : "+
                    todonote;

        }
        todolistview.setText(info);
        mydb.close();
    }


    //delete todolist items
    public void deleteItem(){

        DatabaseHelper db = new DatabaseHelper(getActivity());
        SQLiteDatabase mydb = db.getWritableDatabase();

        int id = Integer.parseInt(deleteId.getText().toString());

        if (deleteId == null) {
            Toast.makeText(getActivity(), "Enter valid id", Toast.LENGTH_LONG).show();
        }

        db.deleteTodolist(id,mydb);
        db.close();
        deleteId.setText("");
        openTodolistFragment();
        Toast.makeText(getActivity(),"Item removed successfully", Toast.LENGTH_SHORT).show();

    }

    public void openTodolistFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new TodoListView());
        ft.commit();

    }





    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}