package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Recherche extends AppCompatActivity {


    private String token = "92ab21b9b91fec38b3611c28cb06b710";





    private Button rechercher;
    private EditText rechercheFilm;
    private DatePicker date;

    private Spinner spinnerGenre;

    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercher = findViewById(R.id.searchBtnid);

        rechercheFilm = findViewById(R.id.searchid);
        date = findViewById(R.id.datePickerFilm);


        spinnerGenre = findViewById(R.id.spinnerGenreid);


        Ion.with(getApplicationContext()).load("https://api.themoviedb.org/3/genre/movie/list?api_key="+token+"&language=fr").asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                JsonArray JsonGenre = result.get("genres").getAsJsonArray();
                List<String> listGenre = new ArrayList<String>();
                for(int i =0; i<JsonGenre.size();i++){

                    listGenre.add(JsonGenre.get(i).getAsJsonObject().get("name").getAsString());

                }
                ArrayAdapter<String> adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listGenre);
                spinnerGenre.setAdapter(adapterList);
            }
        });





        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent resultatActivity = new Intent(getApplicationContext(), Resultats.class);

                resultatActivity.putExtra("recherche", rechercheFilm.getText().toString()); // envoie du nom tapé dans Resultats.java

                Bundle args = new Bundle();
                args.putSerializable("date", (Serializable) getDate(date));
                resultatActivity.putExtra("date", args); //envoie de la date dans Resultats.java


                resultatActivity.putExtra("genre", spinnerGenre.getSelectedItem().toString()); // envoie du genre dans Resultats.java



                startActivity(resultatActivity);

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