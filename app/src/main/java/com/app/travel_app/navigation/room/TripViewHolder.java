package com.app.travel_app.navigation.room;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.travel_app.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    //private final ImageView tripImage;
    protected final TextView tripItemView;

    public TripViewHolder( View itemView) {
        super(itemView);
        tripItemView = itemView.findViewById(R.id.textView);
    }

}
