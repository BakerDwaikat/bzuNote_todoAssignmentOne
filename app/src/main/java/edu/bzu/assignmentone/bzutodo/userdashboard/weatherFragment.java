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
    TextView temperature;
    ImageView weatherIcon;

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
        weatherIcon = view.findViewById(R.id.weather_icon);
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
                    int humidity = mainObj.getInt("humidity");
                    String icon = secondObj.getJSONObject(0).getString("icon");
                    String imageURL = "http://openweathermap.org/img/wn/" + icon + ".png";
                    String finalResult = df.format(temp) + "\u00B0";
                    updateUI(finalResult,imageURL);
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

    public void updateUI(String temp,String photoURL){
        temperature.setText(temp);
        Glide.with(getView()).load(photoURL).into(weatherIcon);
    }
}
