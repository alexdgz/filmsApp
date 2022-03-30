package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    private ListView listFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
     //   test = findViewById(R.id.textViewtest);

        listFilm = findViewById(R.id.listResults);

        String recherche = getIntent().getStringExtra("recherche");

        Bundle args = getIntent().getExtras();

        ArrayList<Film> listeFilm = args.getParcelableArrayList("listeFilm");
        String genre = getIntent().getStringExtra("genre");
        String nbrFilmAffichage = getIntent().getStringExtra("nbrFilmAffichage");

        System.out.println("listeFilm : CEEEEEEEEEEEEEEE BOOOOOOOOOOOOOOOOONNNNNNNNNNNNN " + listeFilm.toString());

        System.out.println("Film : "+recherche);
        System.out.println("Date : "+listeFilm);
        System.out.println("Genre : "+genre);
        System.out.println("Nombre de film à afficher : "+nbrFilmAffichage);

        recherche = recherche.replace(" ", "+");

        System.out.println("replaaaaaaaaaaaaaaaaaaaccccccccccccccccceeeeeeeeee : "+recherche);

/*

        String test = "https://api.themoviedb.org/3/search/movie?api_key="+token+"&language=fr&query=" +recherche+"&page=1&include_adult=false";

        System.out.println("url : "+test);*/
        Ion.with(getApplicationContext()).load("https://api.themoviedb.org/3/search/movie?api_key="+token+"&language=fr&query="+recherche+"&page=1&include_adult=false").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                //System.out.println("Film rechercher : "+result.toString());

                JsonArray JsonTitre = result.get("results").getAsJsonArray();
                System.out.println("Film rechercher : "+JsonTitre.toString());


                List<String> listTitre = new ArrayList<String>();

                int nbrFilm = Integer.parseInt(nbrFilmAffichage);

                if(nbrFilm>JsonTitre.size()){
                    nbrFilm = JsonTitre.size();
                }
                for(int i =0; i<nbrFilm;i++){
                    listTitre.add(JsonTitre.get(i).getAsJsonObject().get("original_title").getAsString());
                }

                ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listTitre);
                listFilm.setAdapter(arrayAdapt);
            }
        });

    }

}