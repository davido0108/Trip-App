package com.app.travel_app.navigation.room;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.travel_app.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    //private final ImageView tripImage;
    protected final TextView tripTextView;
    protected final TextView destinationTextView;
    protected final RatingBar tripRatingBar;
    protected final ImageView imageView;

    public TripViewHolder( View itemView) {
        super(itemView);
        tripTextView = itemView.findViewById(R.id.textView);
        destinationTextView = itemView.findViewById(R.id.destination);
        tripRatingBar = itemView.findViewById(R.id.tripRatingBar);
        imageView = itemView.findViewById(R.id.imageView);
    }

}
