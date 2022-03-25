package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class Recherche extends AppCompatActivity {


    private String token = "92ab21b9b91fec38b3611c28cb06b710";

    private Button rechercher;
    private EditText rechercheFilm;

    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercher = findViewById(R.id.searchBtnid);

        rechercheFilm = findViewById(R.id.searchid);

        testText = findViewById(R.id.textViewtest);


        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Ion.with(view.getContext()).load("https://api.themoviedb.org/3/search/movie?api_key="+token+"&language=fr&query=" +rechercheFilm.getText()+"&page=1&include_adult=false").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                    Intent resultatActivity = new Intent(getApplicationContext(), Resultats.class);

                        resultatActivity.putExtra("resultat", String.valueOf(result));

                        startActivity(resultatActivity);

                    }
                });

            }
        });




    }
}