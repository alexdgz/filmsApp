package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Recherche extends AppCompatActivity {


    private String token = "92ab21b9b91fec38b3611c28cb06b710";

    private Button rechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercher = findViewById(R.id.searchBtnid);


        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Ion.with(view.getContext()).load("https://nosql-workshop.herokuapp.com/api/installations/search?query=" + commune.getText()).asJsonArray().setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        nomActivite.setText(String.valueOf(result));
                    }
                });

            }
        });




    }
}