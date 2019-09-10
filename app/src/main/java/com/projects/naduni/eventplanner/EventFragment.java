package com.projects.naduni.eventplanner;

//import android.app.Fragment;
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

/**
 * Created by inushaV on 8/31/2019.
 */

public class EventFragment extends Fragment {

    View myView;
    Button creat_event_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Set title
        getActivity().setTitle("Event View and Search");

        myView = inflater.inflate(R.layout.fregment_event, container, false);
        creat_event_button = (Button) myView.findViewById(R.id.button_create_event);

//        creat_event_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), CreateEvent.class);
//                EventFragment.this.startActivity(intent);
//            }
//        });

        creat_event_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new CreateEvent());
                ft.commit();

            }});

        return myView;

    }
}
