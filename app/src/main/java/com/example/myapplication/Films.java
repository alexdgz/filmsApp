package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Films {
    public List<Film> listFilm = null;

    public Films(){
        listFilm = new ArrayList<>();
    }

    public List<Film> getListFilm() {
        return listFilm;
    }

    public void addFilm(Film film){
        listFilm.add(film);
    }
    public void removeFilm(Film film){
        listFilm.remove(film);
    }
}
