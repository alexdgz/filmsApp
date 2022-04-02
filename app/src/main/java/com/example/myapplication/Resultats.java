package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Resultats extends AppCompatActivity {

    private String token = "92ab21b9b91fec38b3611c28cb06b710";

    private TextView test;



    private ListView listResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        listResults = findViewById(R.id.listResults);

        Bundle args = getIntent().getExtras();
        String nbrFilmAffichage = getIntent().getStringExtra("nbrFilmAffichage");

        ArrayList<Film> listeFilm = new ArrayList<>();
        listeFilm.clear();
        listeFilm = args.getParcelableArrayList("listeFilm");

        System.out.println("LISTE AVANT LE FOR : "+listeFilm);

        ArrayList<Film> listeFilmRecherche = new ArrayList<>();
        listeFilmRecherche.clear();


        int nbrFilm = Integer.parseInt(nbrFilmAffichage);

        if (nbrFilm > listeFilm.size()) {
            nbrFilm = listeFilm.size();
        }
        for (int i = 0; i < nbrFilm; i++) {
            listeFilmRecherche.add(listeFilm.get(i));
        }

        listResults.setAdapter(new AdapterFilm(getApplicationContext(), listeFilmRecherche));


        System.out.println("LES FILM PENDANT LA LISTE"+listeFilm);
        listResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Film filmDetail = listeFilmRecherche.get(i);

                System.out.println("CLICK POUR LE DETAIL : "+filmDetail);
                Intent intent = new Intent(getApplicationContext(), Detail.class);
                intent.putExtra("film", filmDetail);
                startActivity(intent);
            }
        });

    }

}