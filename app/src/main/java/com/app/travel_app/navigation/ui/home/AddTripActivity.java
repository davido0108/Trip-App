package com.app.travel_app.navigation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.travel_app.R;

public class AddTripActivity extends AppCompatActivity implements Listener{


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText editText;

    private TextView resultFromReceivedTrip;
    private String tripName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_trip);
        editText = findViewById(R.id.insert_trip_name);
        resultFromReceivedTrip = findViewById(R.id.receive_text_view);
    }

    @Override
    public void addTrip(String trip){
        resultFromReceivedTrip.setText(trip);

        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(editText.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String word = editText.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

}
