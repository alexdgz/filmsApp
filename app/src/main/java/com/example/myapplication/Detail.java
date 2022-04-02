package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Detail extends AppCompatActivity {



    private String token = "92ab21b9b91fec38b3611c28cb06b710";


    private TextView titreFilm;
    private TextView dateFilm;
    private TextView descriptionFilm;
    private ImageView imageFilm;

    private ListView listeGenres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        titreFilm = findViewById(R.id.titreFilmid);
        dateFilm = findViewById(R.id.dateFilmid);
        descriptionFilm = findViewById(R.id.descriptionFilmid);
        imageFilm = findViewById(R.id.afficheFilmid);

        listeGenres = findViewById(R.id.genresFilmId);

        Film film = (Film) getIntent().getParcelableExtra("film");

        Ion.with(getApplicationContext()).load("https://api.themoviedb.org/3/movie/"+film.getId()+"?api_key="+token+"&language=fr").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                JsonArray genres = result.getAsJsonArray("genres");
                int i = 0;
                List<String> liste = new ArrayList<>();
                for(JsonElement current: genres){
                    liste.add(current.getAsJsonObject().get("name").getAsString());
                    System.out.println("Numéro :"+i+" : "+current.getAsJsonObject().get("name").getAsString());
                    i++;
                }
                System.out.println("Liste : "+liste);
                film.setGenre(liste);
                listeGenres.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, film.getGenre()));

            }
        });



        System.out.println("LE FILM : "+film);

        System.out.println(film.toString());

        titreFilm.setText(film.getName());
        dateFilm.setText(film.getYear());
        descriptionFilm.setText(film.getDescription());
        Picasso.get().load(film.getImage()).into(imageFilm);

        System.out.println("Film ICIII : "+film.getId());

        List<String> listeGenresFilm = new ArrayList<>();

        //"https://api.themoviedb.org/3/movie/"+recherche+"?api_key="+token+"&language=fr"




        //System.out.println("LISTE GENRES : "+film.getGenre());






        //"https://api.themoviedb.org/3/movie/"+film.getId()+"?api_key="*token "&language=fr"




    }
}