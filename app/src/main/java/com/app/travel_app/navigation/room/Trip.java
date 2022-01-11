package com.app.travel_app.navigation.room;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "trip_table")
public class Trip implements Serializable {

    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="destination")
    private String destination;
    @ColumnInfo(name="type")
    private TripType tripType;
    @ColumnInfo(name="price")
    private int price;
    @ColumnInfo(name="rating")
    private int rating;
    @ColumnInfo(name="startDate")
    private String startDate;
    @ColumnInfo(name="endDate")
    private String endDate;
    @Ignore
    private int image;

    public Trip(String name, String destination, TripType tripType, int price, int rating, String startDate, String endDate/*, Bitmap image*/) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        //this.image = image;
        this.tripType = tripType;
    }


    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", tripType=" + tripType +
                ", price=" + price +
                ", rating=" + rating +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", image=" + image +
                '}';
    }
}
