package com.app.travel_app.navigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.travel_app.R;

public class AddTripFragment extends Fragment {
    private EditText editText;

    private Button saveButton;

    public AddTripFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        editText = view.findViewById(R.id.insert_trip_name);
        saveButton = view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(view1->sendTripToActivity());
        return view;
    }

    public void sendTripToActivity() {
        String tripName = editText.getText().toString();
        Listener listener = (Listener)getActivity();
        listener.addTrip(tripName);
    }
}
