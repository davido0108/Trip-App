package com.app.travel_app.navigation.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepo {

    private TripDao tripDao;
    private LiveData<List<Trip>> trips;

    TripRepo(Application application){
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        tripDao = db.tripDao();
        trips = tripDao.getAlphabetizedTrips();
    }

/*    public ArrayList<Trip> getTrips(){
        trips.add(new Trip("Trip 1","Destinatie 1", 4.0d,3,"22/10/17","22/11/17","image1"));
        trips.add(new Trip("Trip 2","Destinatie 2", 4.0d,3,"22/10/17","22/11/17","image1"));
        trips.add(new Trip("Trip 3","Destinatie 3", 4.0d,3,"22/10/17","22/11/17","image1"));
        trips.add(new Trip("Trip 4","Destinatie 4", 4.0d,3,"22/10/17","22/11/17","image1"));
        trips.add(new Trip("Trip 5","Destinatie 5", 4.0d,3,"22/10/17","22/11/17","image1"));
        return trips;
    }*/

    LiveData<List<Trip>> getAllWords() {
        return trips;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Trip word) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insert(word);
        });
    }

}
