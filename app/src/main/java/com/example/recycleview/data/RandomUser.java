package com.example.recycleview.data;

import android.util.Log;

import androidx.annotation.FontRes;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class RandomUser {
    public String main;
    public String temp;
    public String humidity;
    public String name;
    public String picture;

    public static ArrayList<RandomUser>getUser(JSONObject response){
        ArrayList<RandomUser>users=new ArrayList<>();

        try {
            JSONArray info=response.getJSONArray("list");
            for (int i=0;i<info.length();i++){
                RandomUser aux=new RandomUser();

                aux.name=info.getJSONObject(i).getString("name");
                aux.temp=info.getJSONObject(i).getJSONObject("main").getString("temp");
                aux.humidity=info.getJSONObject(i).getJSONObject("main").getString("humidity");
                aux.main=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main");
                aux.picture=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");



                users.add(aux);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return users;


    }

}
