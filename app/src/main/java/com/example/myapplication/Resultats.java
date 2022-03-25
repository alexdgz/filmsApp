package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;

public class Resultats extends AppCompatActivity {

    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        test = findViewById(R.id.textViewtest);

        JsonObject resultats = getIntent().getParcelableExtra("resultats");

      //  test.setText(String.valueOf(resultats));



    }
}