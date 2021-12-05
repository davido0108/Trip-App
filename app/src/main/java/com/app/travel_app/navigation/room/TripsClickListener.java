package com.app.travel_app.navigation.room;

import android.view.View;

public interface TripsClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
