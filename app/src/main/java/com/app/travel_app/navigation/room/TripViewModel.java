package com.app.travel_app.navigation.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.travel_app.navigation.room.Trip;
import com.app.travel_app.navigation.room.TripRepo;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepo tripRepo;

    private LiveData<List<Trip>> allTrips;

    public TripViewModel(Application application){
        super(application);
        tripRepo = new TripRepo(application);
        allTrips = tripRepo.getAllWords();
    }

    public LiveData<List<Trip>> getAllTrips(){
        return allTrips;
    }

    public void insert(Trip trip){
        tripRepo.insert(trip);
    }
}
