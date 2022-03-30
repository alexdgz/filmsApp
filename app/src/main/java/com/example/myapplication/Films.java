package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Films implements java.io.Serializable {
    private ArrayList<Film> listFilm = null;

    public Films(){
        listFilm = new ArrayList<>();
    }
    public ArrayList<Film> getListFilm(){
        return listFilm;
    }


    public void addFilm(Film film){
        listFilm.add(film);
    }
    public void removeFilm(Film film){
        listFilm.remove(film);
    }


    @Override
    public String toString() {
        return "Films{" +
                "listFilm=" + listFilm +
                '}';
    }
}
