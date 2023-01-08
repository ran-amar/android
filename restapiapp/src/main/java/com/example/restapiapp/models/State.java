package com.example.restapiapp.models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private String nativeName;
    private ArrayList<String> borders = null;
    private Bitmap flag;

    public State() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public Bitmap getFlag() {
        return this.flag;
    }

    public void setFlag(Bitmap flag) {
        this.flag = flag;
    }

    public State(String name, String nativeName, ArrayList<String> borders, Bitmap flag) {
        this.name = name;
        this.nativeName = nativeName;
        this.borders = borders;
        this.flag = flag;
    }
}
