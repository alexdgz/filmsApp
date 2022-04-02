package com.example.myapplication;

import static com.example.myapplication.R.id.imageFilmPreview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterFilm extends ArrayAdapter<Film> {


    ImageView imageFilmPreview;



    public AdapterFilm(Context context, ArrayList<Film> items) {
        super(context, R.layout.linear_layout, items);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        row = inflater.inflate(R.layout.linear_layout, parent, false);

        // personnalisation de la vue
        Film film = getItem(position);

        TextView taches = (TextView)row.findViewById(R.id.nomFilm);
        taches.setText(film.getName());


        imageFilmPreview =  row.findViewById(R.id.imageFilmPreview);


        Picasso.get().load(film.getImage()).into(imageFilmPreview);

        return(row);
    }
}


