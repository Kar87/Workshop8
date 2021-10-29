/**
 * Author: Sai Shalini Karaikatte Venugopal
 * Code for getting the Agent details from the REST service and 
 * display in a formatted manner
 */

package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class AgentActivity extends AppCompatActivity {

    ListView lvAgents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);


        lvAgents = findViewById(R.id.lvAgents);
        ArrayList<Agent> agentList = new ArrayList<>();

        //url for REST service
        String URL ="http://10.0.2.2:8080/JSPDay7-1.0-SNAPSHOT/api/agent/getagents";
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
                                //add Agent from JSON to agentArrayList
                                agentArrayList.add(agt);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //using class AgentListAdapter to format the Agentdisplay
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

