package com.app.travel_app.navigation.edit_trip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.travel_app.R;
import com.app.travel_app.navigation.room.Trip;
import com.app.travel_app.navigation.room.TripType;
import com.app.travel_app.navigation.ui.home.Listener;

import java.util.Calendar;

public class EditTripActivity extends AppCompatActivity implements Listener {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private Trip trip;

    final Calendar calendar = Calendar.getInstance();
    private TextView selectedStartDate,selectedEndDate;
    private EditText name;
    private EditText destination;
    private SeekBar price;
    private TextView startDate;
    private TextView endDate;
    private RatingBar ratingBar;
    private RadioGroup type_rb;
    private RadioButton radioButton;
    private TripType tripType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
             this.trip = (Trip)getIntent().getSerializableExtra("Trip");

        }

        setUp();
        name.setText(trip.getName());
        destination.setText(trip.getDestination());
        ratingBar.setRating(trip.getRating());


    }

    public void setUp(){
        selectedStartDate = findViewById(R.id.update_startdate_textview);
        selectedEndDate = findViewById(R.id.update_enddate_textview);
        name = findViewById(R.id.update_trip_name);
        destination = findViewById(R.id.update_trip_destination);
        price = findViewById(R.id.update_price);
        ratingBar = findViewById(R.id.ratingBar2);



    }
    public void onRadioUpdateButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.update_radio_city_break:
                if (checked)
                    tripType = TripType.CITY_BREAK;
            case R.id.update_radio_sea_side:
                if (checked)
                    tripType = TripType.SEA_SIDE;
            case R.id.update_radio_mountains:
                if (checked)
                    tripType = TripType.MOUNTAINS;

        }
    }

    public void updateStartDateOpenDatePickerOnClick(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        selectedEndDate.setText(String.format("%d-%d-%d", dayOfMonth, monthOfYear + 1, year));

                    }
                }, year, month, day);
        datePickerDialog.show();
    }
    public void updateEndDateOpenDatePickerOnClick(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        selectedStartDate.setText(String.format("%d-%d-%d", dayOfMonth, monthOfYear + 1, year));

                    }
                }, year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void addTrip(Trip trip) {

    }

    @Override
    public void updateTrip() {

        trip.setName(name.getText().toString());
        trip.setDestination(destination.getText().toString());
        trip.setTripType(tripType);
        trip.setStartDate(selectedStartDate.getText().toString());
        trip.setEndDate(selectedEndDate.getText().toString());
        trip.setRating((int)ratingBar.getRating());
        trip.setPrice(price.getProgress());


        Toast.makeText(getApplicationContext(), "triptype: " + tripType, Toast.LENGTH_SHORT).show();

        Intent replyIntent = new Intent();

        if (trip == null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(EXTRA_REPLY, trip);
            setResult(3, replyIntent);
        }

        finish();
    }

    @Override
    public void deleteTrip() {
        Intent replyIntent = new Intent();

        if (trip == null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(EXTRA_REPLY, trip);
            setResult(RESULT_OK, replyIntent);
        }

        finish();

        //tripViewModel.delete(position);
    }
}
