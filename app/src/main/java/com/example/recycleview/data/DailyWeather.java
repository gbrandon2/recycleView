package com.example.recycleview.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DailyWeather {

    public String temp;
    public String main;
    public String humidity;
    public String date;
    public String picture;
    public String name;

    public static ArrayList<DailyWeather> getDailyWeather(JSONObject response){
        ArrayList<DailyWeather>days=new ArrayList<>();

        try {
            JSONArray info=response.getJSONArray("list");
            int cont=1;
            for (int i=0;i<info.length();i++){
                DailyWeather aux=new DailyWeather();
                aux.temp=info.getJSONObject(i).getJSONObject("main").getString("temp");
                aux.main=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main");
                aux.picture=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");
                aux.humidity=info.getJSONObject(i).getJSONObject("main").getString("humidity");
                //aux.date="Dia "+cont+" " +info.getJSONObject(i).getString("dt_txt").substring(0,10);
                aux.date=info.getJSONObject(i).getString("dt_txt");
                aux.name=response.getJSONObject("city").getString("name");
                cont++;
                boolean sw=false;
              for (int j=0;j<days.size();j++){
                  if(days.get(j).date.equals(aux.date)){
                      sw=true;
                  }

              }
              if(sw=false)
                days.add(aux);









                days.add(aux);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return days;


    }

}
