package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Recherche extends AppCompatActivity {


    private String token = "92ab21b9b91fec38b3611c28cb06b710";


    private Films listeFilms;

    private HashMap genreToid;



    private Button rechercher;
    private EditText rechercheFilm;
    private DatePicker date;

    private Spinner spinnerGenre;

    private SeekBar seekBarNbrFilm;
    private TextView nbrFilmSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        genreToid = new HashMap();


        listeFilms = new Films();

        rechercher = findViewById(R.id.searchBtnid);

        rechercheFilm = findViewById(R.id.searchid);
        date = findViewById(R.id.datePickerFilm);


        spinnerGenre = findViewById(R.id.spinnerGenreid);

        seekBarNbrFilm = findViewById(R.id.seekBarNombreid);
        nbrFilmSeekBar = findViewById(R.id.nbrFilmSeekBarid);


        Ion.with(getApplicationContext()).load("https://api.themoviedb.org/3/genre/movie/list?api_key="+token+"&language=fr").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                JsonArray JsonGenre = result.get("genres").getAsJsonArray();
                List<String> listGenre = new ArrayList<String>();
                for(int i =0; i<JsonGenre.size();i++){

                    genreToid.put(JsonGenre.get(i).getAsJsonObject().get("id").getAsInt(), JsonGenre.get(i).getAsJsonObject().get("name").getAsString());

                    listGenre.add(JsonGenre.get(i).getAsJsonObject().get("name").getAsString());

                }



                ArrayAdapter<String> adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listGenre);
                spinnerGenre.setAdapter(adapterList);
            }
        });


        seekBarNbrFilm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nbrFilmSeekBar.setText(""+seekBarNbrFilm.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        rechercheFilm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    rechercheFilm.getText().clear();
                }
            }
        });
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recherche = rechercheFilm.getText().toString().replace(" ", "%20");

                System.out.println("recherche ------------------- : "+recherche);

                Ion.with(getApplicationContext()).load("https://api.themoviedb.org/3/search/movie?api_key="+token+"&language=fr&query="+recherche+"&page=1&include_adult=false").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {


                        //System.out.println("ICIIIIIIIIIIIIIIIIIIIII   "+result);

                        JsonArray JsonFilm = result.get("results").getAsJsonArray();

                        System.out.println("JsonFilm : ---------------- "+JsonFilm.toString());

                        Gson gson = new Gson();
                        for(JsonElement current: JsonFilm){


                            //System.out.println("current image : ---------------- "+current.getAsJsonObject().get("poster_path"));

                            String name = gson.toJson(current.getAsJsonObject().get("original_title")).replace("\"","");
                            String description = gson.toJson(current.getAsJsonObject().get("overview")).replace("\"","");
                            String image = gson.toJson("https://image.tmdb.org/t/p/w500"+current.getAsJsonObject().get("poster_path")).replace("\\\"","").replace("\"",""); //pour enlever le \" qui se trouve dans l'url de l'image


                            System.out.println("current image : ---------------- "+image);



                            List genres = new ArrayList();
                            for(JsonElement currentGenre: current.getAsJsonObject().get("genre_ids").getAsJsonArray()){
                                genres.add(genreToid.get(currentGenre.getAsInt()));
                            }
                            String year = gson.toJson(current.getAsJsonObject().get("release_date")).replace("\"","");
                            String id = gson.toJson(current.getAsJsonObject().get("id")).replace("\"","");

                            Film currentFilm = new Film(name,description,image,genres,year,id);

                            listeFilms.addFilm(currentFilm);
                        }


                        Intent resultatActivity = new Intent(getApplicationContext(), Resultats.class);

                        resultatActivity.putExtra("recherche", rechercheFilm.getText().toString()); // envoie du nom tapé dans Resultats.java

                        Bundle args = new Bundle();
                        resultatActivity.putParcelableArrayListExtra("listeFilm", listeFilms.getListFilm()); //envoie de la date dans Resultats.java


                        resultatActivity.putExtra("genre", spinnerGenre.getSelectedItem().toString()); // envoie du genre dans Resultats.java
                        resultatActivity.putExtra("nbrFilmAffichage", nbrFilmSeekBar.getText().toString());

                        startActivity(resultatActivity);

                        listeFilms.clear(); // on vide la liste de film pour éviter de récupérer les films de la dernière recherche


                    }
                });




            }
        });

    }
    private List getDate(DatePicker date){
        List dateSelectionner = new ArrayList();

        dateSelectionner.add(date.getDayOfMonth());
        dateSelectionner.add(date.getMonth());
        dateSelectionner.add(date.getYear());

        return dateSelectionner;
    }
}