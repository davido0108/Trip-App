package com.app.travel_app.navigation.room;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="destination")
    private String destination;
    @ColumnInfo(name="price")
    private double price;
    @ColumnInfo(name="ratin")
    private int rating;
    @ColumnInfo(name="startDate")
    private String startDate;
    @ColumnInfo(name="endDate")
    private String endDate;
    @Ignore
    private Bitmap image;

    public Trip(String name, String destination, double price, int rating, String startDate, String endDate/*, Bitmap image*/) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        //this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "Name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
