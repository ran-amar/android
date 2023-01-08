package com.example.restapiapp.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.util.JsonReader;

import androidx.annotation.RequiresApi;

import com.example.restapiapp.models.State;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class DataService {
    private ArrayList<State> stateArr = new ArrayList<State>();

    public ArrayList<State> getStateArr() {
        String sURL = "https://restcountries.com/v2/all?fields=name,nativeName,borders,flags";
        URL url = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            url = new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection req = null;
        try {
            assert url != null;
            req = (HttpURLConnection) url.openConnection();
            req.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonParser jp = new JsonParser();
        try {
            assert req != null;
            JsonElement root =  jp.parse(new InputStreamReader((InputStream) req.getContent()));
            JsonArray rootObj = root.getAsJsonArray();

            for(JsonElement je: rootObj) {
                JsonObject obj = je.getAsJsonObject();

                JsonElement entryName = obj.get("name");
                JsonElement entryNativeName = obj.get("nativeName");
                JsonElement entryFlag = obj.get("flags").getAsJsonObject().get("png");
                JsonElement entryBorders = obj.get("borders");

                String name = entryName.toString().replace("\"", "").trim();
                String nativeName = entryNativeName.toString().replace("\"", "").trim();

                Bitmap flag = download_Image(entryFlag.toString().replace("\"", "").trim());

                ArrayList<String> borders = new ArrayList<String>();
                if(entryBorders != null) {
                    JsonArray borderArr = entryBorders.getAsJsonArray();

                    for (JsonElement j: borderArr){
                        String s = j.toString().replace("/", "").trim();
                        borders.add(s);
                    }
                }

                // State state = new State(name, nativeName , borders, flag);
                this.stateArr.add(new State(name, nativeName,borders, flag));


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.stateArr;
    }

    private Bitmap download_Image(String url) {
        Bitmap avatarBmp = null;
        try{
            InputStream in = new java.net.URL(url).openStream();
            avatarBmp = BitmapFactory.decodeStream(in);
            in.close();
            if (null != avatarBmp)
                return avatarBmp;

        }catch(Exception e){}
        return avatarBmp;
    }
}
