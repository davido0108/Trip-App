package com.app.travel_app.navigation.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.travel_app.R;
import com.app.travel_app.navigation.edit_trip.EditTripActivity;
import com.app.travel_app.navigation.room.RecyclerTouchListener;
import com.app.travel_app.navigation.room.Trip;
import com.app.travel_app.navigation.room.TripAdapter;
import com.app.travel_app.navigation.room.TripType;
import com.app.travel_app.navigation.room.TripViewModel;
import com.app.travel_app.navigation.room.TripsClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {


    private static final int NEW_TRIP_ACTIVITY_REQUEST_CODE=1;
    private static final int EDIT_ACTIVITY_REQUEST_CODE = 2;
    private static final int SORT_TRIPS_ACTIVITY_REQUEST_CODE = 3;
    private RecyclerView tripsRecyclerView;
    private TripViewModel tripViewModel;


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
        FloatingActionButton fab_sort = view.findViewById(R.id.fab_sort);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNavIntent = new Intent( getContext(), AddTripActivity.class);
                startActivityForResult(startNavIntent, NEW_TRIP_ACTIVITY_REQUEST_CODE);
            }
        });

        fab_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNavIntent = new Intent( getContext(), SortActivity.class);
                startActivityForResult(startNavIntent, SORT_TRIPS_ACTIVITY_REQUEST_CODE);
            }
        });

        setRecyclerViewListener(adapter);

        return view;

    }

    private void setTripsAdapter(TripAdapter adapter){
        tripsRecyclerView.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if(data.getExtras() !=null) {
                Trip trip = (Trip) data.getSerializableExtra(EditTripActivity.EXTRA_REPLY);
                tripViewModel.insert(trip);


            }
        }
        else if(requestCode == EDIT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            if(data.getExtras() !=null) {
                Trip trip = (Trip) data.getSerializableExtra(EditTripActivity.EXTRA_REPLY);
                tripViewModel.delete(trip);


            }
        }
        else if(requestCode == EDIT_ACTIVITY_REQUEST_CODE && resultCode == 3){
            if(data.getExtras() !=null) {
                Trip trip = (Trip) data.getSerializableExtra(EditTripActivity.EXTRA_REPLY);
                if (trip.getTripType() == TripType.MOUNTAINS)
                    trip.setImage(R.drawable.mountain);
                else if (trip.getTripType() == TripType.SEA_SIDE )
                    trip.setImage(R.drawable.sea);
                else
                    trip.setImage(R.drawable.city_break);
                tripViewModel.update(trip);

            }
        }
        else if(requestCode == SORT_TRIPS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            if(data.getExtras() !=null) {
                String tripTypeExtra = data.getStringExtra("tripType");

                Toast.makeText(getContext(),"Trip type selected: " + tripTypeExtra, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(
                    getContext(),
                    "Empty",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setRecyclerViewListener(TripAdapter adapter){
        tripsRecyclerView.addOnItemTouchListener((new RecyclerTouchListener(getContext(),
                tripsRecyclerView, new TripsClickListener() {
            @Override
            public void onClick(View view, int position) {


            }

            @Override
            public void onLongClick(View view, int position) {

                Trip trip = adapter.getTrip(position);
                trip.setId(adapter.getTrip(position).getId());

                Intent intent = new Intent(getContext(), EditTripActivity.class);
                intent.putExtra("Trip", trip);
                startActivityForResult(intent, EDIT_ACTIVITY_REQUEST_CODE);



            }
        })));
    }


}
