package com.app.travel_app.navigation.room;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepo {

    private TripDao tripDao;
    private LiveData<List<Trip>> trips;
    private LiveData<List<Trip>> sortedTrips;

    TripRepo(Application application){
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        tripDao = db.tripDao();
        trips = tripDao.getAlphabetizedTrips();
    }


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

    public void update(Trip trip){
        TripRoomDatabase.databaseWriteExecutor.execute(()->{
            tripDao.update(trip);
        });
    }

    public void remove(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(()->{
            tripDao.delete(trip);
        });
    }

    LiveData<List<Trip>> sortByTrip(String tripType) {


        TripRoomDatabase.databaseWriteExecutor.execute(()->{

            sortedTrips = tripDao.getTrips(tripType);

        });
        return  sortedTrips;
    }
}
