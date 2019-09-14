package com.projects.naduni.eventplanner;

//import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;

/**
 * Created by inushaV on 8/31/2019.
 */

public class EventFragment extends Fragment {

    View myView;
    Button creat_event_button;
    private TextView event_display;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Set title
        getActivity().setTitle("Events");

        myView = inflater.inflate(R.layout.fregment_event, container, false);
        creat_event_button = (Button) myView.findViewById(R.id.button_create_event);
        event_display = myView.findViewById(R.id.event_display);

        AnimationDrawable animationDrawable = (AnimationDrawable) myView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        creat_event_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new CreateEvent());
                ft.commit();

            }});
        readEvents();
        return myView;

    }


    //retrieve guests from db
    private void readEvents(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getEvents(db);

        Button b1 = myView.findViewById(R.id.view_btn);

        String info = " ";

        while(cursor.moveToNext())
        {

            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
            String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_DATE));

            info =info + "\n\n"+ name +"\t\t\t\t"+
                    date + b1;
            event_display.setText(info);
        }

        mydb.close();

    }
}
