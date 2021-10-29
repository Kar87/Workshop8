package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AgentActivity extends AppCompatActivity {

    ListView lvAgents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);


        lvAgents = findViewById(R.id.lvAgents);
        ArrayList<Agent> agentList = new ArrayList<>();

        String URL ="http://10.0.2.2:8080/JSPDay7-1.0-SNAPSHOT/api/agent/getagents";

//        AgentListAdapter adapter = new AgentListAdapter(this, R.layout.layout_agent_listview, dataSource.getAllPackages());
//        lvAgents.setAdapter(adapter);


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<String> tubeLines = new ArrayList<String>();
                        ArrayList<Agent> agentArrayList = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {

                            // Get current json object
                            JSONObject line = null;
                            try {
                                line = response.getJSONObject(i);
                                Integer agentid = line.getInt("agentid");
                                String agtfirstname = line.getString("agtfirstname");
                                String agtmiddleinitial = line.getString("agtmiddleinitial");
                                String agtlastname = line.getString("agtlastname");
                                String agtbusphone = line.getString("agtbusphone");
                                String agtemail = line.getString("agtemail");
                                String agtposition = line.getString("agtposition");
                                Integer agencyid = line.getInt("agencyid");
                                Agent agt = new Agent(agentid, agtfirstname, agtmiddleinitial, agtlastname,
                                        agtbusphone, agtemail, agtposition, agencyid);
                                agentArrayList.add(agt);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // Get the current line (json object) data
                            // To avoid crash use optString
                            String lineName = line.toString();

                            // add all items
                            tubeLines.add(lineName);


                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AgentActivity.this, android.R.layout.simple_list_item_1, tubeLines);
                        //ArrayAdapter<Agent> arrayAdapter1 = new ArrayAdapter<>(AgentActivity.this, android.R.layout.simple_list_item_1,agentArrayList);
                        AgentListAdapter adapter = new AgentListAdapter(AgentActivity.this, R.layout.layout_agent_listview, agentArrayList);
                        lvAgents.setAdapter(adapter);
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
}

