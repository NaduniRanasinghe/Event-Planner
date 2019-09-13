package com.projects.naduni.eventplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

public class TodoListAdd extends Fragment {

    EditText addTodos,addNotes;
    DatabaseHelper myDb;
    Button button;
    TextView txt1,txt2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getActivity().setTitle("To-Do List Add");
        View view = inflater.inflate(R.layout.todolist_add, container, false);
        addTodos = (EditText)view.findViewById(R.id.addtodos);
        addNotes = (EditText)view.findViewById(R.id.addnotes);
        button = (Button) view.findViewById(R.id.todolistbutton);
        txt1 = (TextView)view.findViewById(R.id.textView1);
        txt2 = (TextView)view.findViewById(R.id.textView2);
        myDb = new DatabaseHelper(getActivity());



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(myDb.insertData(addTodos.getText().toString(),addNotes.getText().toString())){
                    Toast.makeText(getActivity(),"Succesfully Added the item !", Toast.LENGTH_LONG ).show();
                }
                else{
                    Toast.makeText(getActivity(), "Not added an item ", Toast.LENGTH_LONG ).show();
                }
            }});
        return view;
    }


}
