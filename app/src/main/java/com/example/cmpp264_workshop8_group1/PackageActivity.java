package com.example.cmpp264_workshop8_group1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;


/*
Author: Aidan Goguen
PackageActivity created by Aidan Goguen, loadData method created by Shalini 
 */

public class PackageActivity extends AppCompatActivity {
    ListView lvPackage;
    PackageDB dataSource;
    //url for REST service
    String URL ="http://10.0.2.2:8080/JSPDay7-1.0-SNAPSHOT/api/package/getpackages";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        lvPackage = findViewById(R.id.lvPackage);
        dataSource = new PackageDB(this);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");


        loadData(URL);
        lvPackage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), SelectedPackageActivity.class);
                intent.putExtra("pkg", (Serializable) lvPackage.getAdapter().getItem(position));
                intent.putExtra("user", user);
                intent.putExtra("password", pwd);
                startActivity(intent);
            }
        });
    }


    /**
     * Author: Sai Shalini Karaikatte Venugopal
     * Code for getting the Package details from the REST service and 
     * display in a formatted manner
     */
    private void loadData(String URL){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<String> tubeLines = new ArrayList<>();
                        ArrayList<Package> packageArrayList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                        // Get current json object
                            JSONObject line = null;
                            try {
                                line = response.getJSONObject(i);
                                Integer id = line.getInt("packageid");
                                String name = line.getString("pkgname");
                                String strtdate = line.getString("pkgstartdate");
                                String enddate = line.getString("pkgenddate");
                                String desc = line.getString("pkgdesc");
                                String baseprice = line.getString("pkgbaseprice");
                                String commission = line.getString("pkgagencycommission");
                                Package pkg = new Package(id, name, strtdate, enddate, desc, baseprice, commission);
                                packageArrayList.add(pkg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                      
                        PackageListAdapter adapter = new PackageListAdapter(PackageActivity.this, R.layout.layout_package_listview, packageArrayList);
                        lvPackage.setAdapter(adapter);                      
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                }
        );
        requestQueue.add(arrayRequest);
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadData(URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(URL);
    }
}