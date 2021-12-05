package com.app.travel_app.navigation.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.travel_app.R;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Trip> trips;

    public TripAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.card_view_trip, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        if (trips != null) {
            Trip current = trips.get(position);
            holder.tripItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.tripItemView.setText("No Words");
        }
    }

    @Override
    public int getItemCount() {
        if (trips != null)
            return trips.size();
        else return 0;
    }

    public void setTrips(List<Trip> trips){
        this.trips = trips;
        notifyDataSetChanged();
    }
}
