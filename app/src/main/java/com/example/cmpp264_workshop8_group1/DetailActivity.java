package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    EditText etPackages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etPackages = findViewById(R.id.etPackages);
        etPackages.setText("Announcements \n\n\n\n\n Insert announcements here \n\n Insert announcements here \n\n Insert announcements here");

        //String URL ="http://10.0.2.2:8080/JSPDay7-1.0-SNAPSHOT/api/package/getpackages";


//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        JsonArrayRequest arrayRequest = new JsonArrayRequest(
//                Request.Method.GET,
//                URL,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.e("Rest Response", response.toString());
//                        etPackages.setText(response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Rest Response", error.toString());
//                    }
//                }
//        );
//        requestQueue.add(arrayRequest);
    }
}