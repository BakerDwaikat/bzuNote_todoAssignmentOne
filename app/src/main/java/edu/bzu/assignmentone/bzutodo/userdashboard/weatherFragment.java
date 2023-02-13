package edu.bzu.assignmentone.bzutodo.userdashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import edu.bzu.assignmentone.bzutodo.R;

public class weatherFragment extends Fragment {

    private DecimalFormat df = new DecimalFormat("#.#");
    TextView temperature, description,minTemp,maxTemp,Humidity, feelslike;
    ImageView weatherIcon;

    String photoURL;

    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String code = "cda8ea9523bfbcbc0d04f3193e59aeef";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        temperature = view.findViewById(R.id.temperature);
        description = view.findViewById(R.id.description);
        weatherIcon = view.findViewById(R.id.weather_icon);
        Humidity = view.findViewById(R.id.Humidity);
        minTemp = view.findViewById(R.id.min_temp);
        maxTemp = view.findViewById(R.id.max_temp);
        feelslike = view.findViewById(R.id.feels_like);
        String str = url + "?q=Ramallah&appid=" + code;
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, str, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String result = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject mainObj = jsonObject.getJSONObject("main");
                    JSONArray secondObj = jsonObject.getJSONArray("weather");
                    double temp = mainObj.getDouble("temp") - 273.15;
                    double feelsTemp = mainObj.getDouble("feels_like") - 273.15;
                    double mintemp = mainObj.getDouble("temp_min") - 273.15;
                    double maxtemp = mainObj.getDouble("temp_max") - 273.15;
                    int humidity = mainObj.getInt("humidity");
                    String icon = secondObj.getJSONObject(0).getString("icon");
                    String cond = secondObj.getJSONObject(0).getString("description");
                    photoURL = "http://openweathermap.org/img/wn/" + icon + ".png";
                    String tempFinal = df.format(temp) + "\u00B0";
                    String minFinal = df.format(mintemp) + "\u00B0";
                    String maxFinal = df.format(maxtemp) + "\u00B0";
                    String feelsFinal = df.format(feelsTemp) + "\u00B0";
                    updateUI(tempFinal,photoURL,cond,""  + humidity,minFinal,maxFinal,feelsFinal);
                } catch (JSONException e) {
                    Log.e("error",e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response Error",error.toString());
            }
        });
        queue.add(stringRequest);
    }

    public void updateUI(String temp,String photoURL,String cond,String humidity, String mintemp, String maxtemp, String FeelsLike){
        temperature.setText(temp);
        description.setText(cond);
        Humidity.setText(humidity);
        minTemp.setText(mintemp);
        maxTemp.setText(maxtemp);
        feelslike.setText(FeelsLike);
        Glide.with(getView()).load(photoURL).into(weatherIcon);
    }



}
