package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Film implements Parcelable {
    private String name;
    private String description;
    private String image;
    private List genre;
    private String year;
    private String id;

    public Film(String name, String description, String image, List genre, String year, String id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.genre = genre;
        this.year = year;
        this.id = id;
    }

    protected Film(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
        year = in.readString();
        id = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List getGenre() {
        return genre;
    }

    public void addGenre(String genre) {
        this.genre.add(genre);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(year);
        parcel.writeString(id);
    }
}
