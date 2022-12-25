package com.example.recyclerviewapp;


import java.io.Serializable;

public class DataModel implements Serializable {
    String name;
    String details;
    int id;
    byte[] image;

    public DataModel(String name, String details, int id, byte[] image) {
        this.name = name;
        this.details = details;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
