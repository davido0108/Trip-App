package com.app.travel_app.navigation.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.travel_app.R;
import com.app.travel_app.navigation.room.Trip;
import com.app.travel_app.navigation.room.TripType;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AddTripFragment extends Fragment {
    private EditText editText;
    final Calendar calendar = Calendar.getInstance();
    private Button saveButton;
    private TextView selectedStartDate,selectedEndDate;

    private EditText name;
    private EditText destination;
    private SeekBar price;

    private RatingBar ratingBar;
    private Trip trip;
    private RadioGroup type_rb;
    private TextView priceSeekBar;


    public AddTripFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        saveButton = view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(view1->sendTripToActivity());

        selectedStartDate = view.findViewById(R.id.pick_startdate_textview);
        selectedEndDate = view.findViewById(R.id.pick_enddate_textview);

        name = view.findViewById(R.id.insert_trip_name);
        destination = view.findViewById(R.id.insert_destination);
        price = view.findViewById(R.id.pick_price);
        priceSeekBar = view.findViewById(R.id.price_seek_bar);


        ratingBar = view.findViewById(R.id.pick_rating_ratingbar);

        price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceSeekBar.setText(progress+ "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return view;
    }



    public void sendTripToActivity() {


        trip = new Trip(name.getText().toString(), destination.getText().toString(),TripType.CITY_BREAK, price.getProgress(), (int)ratingBar.getRating(), selectedStartDate.toString(), selectedEndDate.toString());
        /*Toast.makeText(getContext(),"trip: "+ name.getText().toString() +destination.getText().toString()*//* +Double.parseDouble(price.getText().toString())+ (int)ratingBar.getRating()+ selectedStartDate.toString()+ selectedEndDate.toString()*//*,
                Toast.LENGTH_SHORT).show();*/
        Listener listener = (Listener)getActivity();
        listener.addTrip(trip);
    }
}
