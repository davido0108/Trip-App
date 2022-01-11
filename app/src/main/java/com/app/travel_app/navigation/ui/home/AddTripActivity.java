package com.app.travel_app.navigation.ui.home;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.travel_app.R;
import com.app.travel_app.navigation.room.Trip;
import com.app.travel_app.navigation.room.TripType;

import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity implements Listener{

    final Calendar calendar = Calendar.getInstance();
        public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText editText;

    private TextView selectedStartDate,selectedEndDate;

    private TripType tripType;
    private int tripImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_trip);

        selectedStartDate = findViewById(R.id.pick_startdate_textview);
        selectedEndDate = findViewById(R.id.pick_enddate_textview);


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_city_break:
                if (checked)
                    tripType = TripType.CITY_BREAK;
            case R.id.radio_sea_side:
                if (checked)
                    tripType = TripType.SEA_SIDE;
            case R.id.radio_mountains:
                if (checked)
                    tripType = TripType.MOUNTAINS;
        }
    }

    public void startDateOpenDatePickerOnClick(View view) {
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
    public void endDateOpenDatePickerOnClick(View view) {
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
    public void addTrip(Trip trip){
        trip.setTripType(tripType);

        Intent replyIntent = new Intent();
        if (trip==null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            replyIntent.putExtra(EXTRA_REPLY, trip);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

    @Override
    public void updateTrip() {

    }

    @Override
    public void deleteTrip() {

    }

}
