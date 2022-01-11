package com.app.travel_app.navigation.ui.home;

import com.app.travel_app.navigation.room.Trip;

public interface Listener {
    void addTrip(Trip trip);
    void updateTrip();
    void deleteTrip();

}
