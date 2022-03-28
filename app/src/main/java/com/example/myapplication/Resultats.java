package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class Resultats extends AppCompatActivity {

    private String token = "92ab21b9b91fec38b3611c28cb06b710";

    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
     //   test = findViewById(R.id.textViewtest);

        String recherche = getIntent().getStringExtra("recherche");
        ArrayList<String> listDateSelectionner = (ArrayList<String>) getIntent().getBundleExtra("date").getSerializable("date");
        String genre = getIntent().getStringExtra("genre");

        System.out.println("Film : "+recherche);
        System.out.println("Date : "+listDateSelectionner);
        System.out.println("Genre : "+genre);




        /*
        Ion.with(view.getContext()).load("https://api.themoviedb.org/3/search/movie?api_key="+token+"&language=fr&query=" +rechercheFilm.getText()+"&page=1&include_adult=false").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                Intent resultatActivity = new Intent(getApplicationContext(), Resultats.class);

                resultatActivity.putExtra("resultat", String.valueOf(result));

                startActivity(resultatActivity);

            }
        });*/


    }
}