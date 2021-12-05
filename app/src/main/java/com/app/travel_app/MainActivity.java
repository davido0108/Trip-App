package com.app.travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.travel_app.navigation.NavActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openNavigationDrawerOnClick(View view){
        Intent startNavIntent = new Intent( MainActivity.this, NavActivity.class);
        startActivity(startNavIntent);
    }



}