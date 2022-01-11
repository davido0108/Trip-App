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
    private LiveData<List<Trip>> sortedTrips;

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

    public void update(Trip trip){
        tripRepo.update(trip);
    }

    public void delete(Trip trip) {
        tripRepo.remove(trip);
    }

    public LiveData<List<Trip>> sortTrips(String tripType){
        sortedTrips = tripRepo.sortByTrip(tripType);
        return sortedTrips;
    }
}
