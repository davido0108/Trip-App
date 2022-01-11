package com.app.travel_app.navigation.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
        TripType tripType;
        if (trips != null) {
            Trip current = trips.get(position);
            holder.tripTextView.setText(current.getName());
            holder.destinationTextView.setText(current.getDestination());
            holder.tripRatingBar.setRating(current.getRating());
           // holder.imageView.setBackgroundColor(current.getImage());
            tripType = current.getTripType();
            if (tripType == TripType.MOUNTAINS)
                holder.imageView.setBackgroundResource(R.drawable.mountain);
            else if (tripType == TripType.SEA_SIDE )
                holder.imageView.setBackgroundResource(R.drawable.sea);
            else
                holder.imageView.setBackgroundResource(R.drawable.city_break);

        } else {
            // Covers the case of data not being ready yet.
            holder.tripTextView.setText("No Words");
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

    public Trip getTrip(int position){

        String tripName = trips.get(position).getName();
        String tripDestination = trips.get(position).getDestination();
        int tripPrice = trips.get(position).getPrice();
        int rating = trips.get(position).getRating();
        String startDate = trips.get(position).getStartDate();
        String endDate = trips.get(position).getStartDate();
        TripType tripType = trips.get(position).getTripType();

        Trip trip = new Trip(tripName, tripDestination,tripType, tripPrice, rating, startDate, endDate);
        trip.setId(trips.get(position).getId());




        return trip;
    }
}
