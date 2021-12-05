package com.app.travel_app.navigation.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.travel_app.MainActivity;
import com.app.travel_app.R;
import com.app.travel_app.navigation.edit_trip.EditTripActivity;
import com.app.travel_app.navigation.room.RecyclerTouchListener;
import com.app.travel_app.navigation.room.Trip;
import com.app.travel_app.navigation.room.TripAdapter;
import com.app.travel_app.navigation.room.TripViewModel;
import com.app.travel_app.navigation.room.TripsClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    private static final int NEW_TRIP_ACTIVITY_REQUEST_CODE=1;
    private RecyclerView tripsRecyclerView;
    private TripViewModel tripViewModel;
    private List<Trip> trips;

    public HomeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tripsRecyclerView  = view.findViewById(R.id.tripRecyclerView);

        final TripAdapter adapter = new TripAdapter(getContext());
        setTripsAdapter(adapter);

        tripsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        tripViewModel.getAllTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                adapter.setTrips(trips);
            }
        });



        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNavIntent = new Intent( getContext(), AddTripActivity.class);
                startActivityForResult(startNavIntent, NEW_TRIP_ACTIVITY_REQUEST_CODE);
            }
        });


        setRecyclerViewListener();

        return view;

    }

    private void setTripsAdapter(TripAdapter adapter){
        tripsRecyclerView.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Trip trip = new Trip(data.getStringExtra(AddTripActivity.EXTRA_REPLY),"Destinatie 1", 4.0d,3,"22/10/17","22/11/17");
            tripViewModel.insert(trip);
        } else {
            Toast.makeText(
                    getContext(),
                    "Empty",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void setRecyclerViewListener(){
        tripsRecyclerView.addOnItemTouchListener((new RecyclerTouchListener(getContext(),
                tripsRecyclerView, new TripsClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "single_click" + position,
                        Toast.LENGTH_SHORT).show();

                /*Trip trip = trips.get(position);
                Intent intent = new Intent(MainActivity.this, EmailDetailsActivity.class);
                intent.putExtra(EMAIL_ID, email.getId());
                startActivity(intent)*/
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent = new Intent(getContext(), EditTripActivity.class);

            }
        })));
    }


}
