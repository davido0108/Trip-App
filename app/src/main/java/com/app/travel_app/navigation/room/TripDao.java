package com.app.travel_app.navigation.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.travel_app.navigation.room.Trip;

import java.util.List;

@Dao
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Query("SELECT * from trip_table ORDER BY name ASC")
    LiveData<List<Trip>> getAlphabetizedTrips();
}
