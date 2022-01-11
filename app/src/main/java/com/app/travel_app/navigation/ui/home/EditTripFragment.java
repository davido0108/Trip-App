package com.app.travel_app.navigation.ui.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.app.travel_app.R;

public class EditTripFragment extends Fragment {

    private Button deleteButton, updateButton;

    public EditTripFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        deleteButton = view.findViewById(R.id.delete_button);
        updateButton = view.findViewById(R.id.update_button);

        deleteButton.setOnClickListener(view1->delete());

        updateButton.setOnClickListener(view2->update());

        return view;
    }


    private void update(){
        Listener listener = (Listener) getActivity();
        listener.updateTrip();

    }

    private void delete() {
        Listener listener = (Listener)getActivity();
        listener.deleteTrip();
    }
}
