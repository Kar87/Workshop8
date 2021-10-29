package com.example.cmpp264_workshop8_group1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
Author: Aidan Goguen
Adapter class that adds display for listview in Agents
 */

public class AgentListAdapter extends ArrayAdapter<Agent> {
    List<Agent> agentList = new ArrayList<>();
    private Context aContext;
    int aResource;

    public AgentListAdapter(Context context, int resource, List<Agent> agentList){
        super(context, resource, agentList);
        this.agentList = agentList;
        aContext = context;
        aResource = resource;
    }

    @Override
    public int getCount() {
        return agentList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get agent information
        Integer AgentId = getItem(position).getAgentid();
        String AgtFirstName = getItem(position).getAgtfirstname();
        String AgtMiddleInitial = getItem(position).getAgtmiddleinitial();
        String AgtLastName = getItem(position).getAgtlastname();
        String AgtBusPhone = getItem(position).getAgtbusphone();
        String AgtEmail = getItem(position).getAgtemail();
        String AgtPosition = getItem(position).getAgtposition();
        Integer AgencyId = getItem(position).getAgencyid();

        Agent agt = new Agent(AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId);

        LayoutInflater inflater = LayoutInflater.from(aContext);
        convertView = inflater.inflate(R.layout.layout_agent_listview, null);

        ImageView AgtViewImg = (ImageView) convertView.findViewById(R.id.AgtViewImg);
        //TextView txtAgentId = (TextView) convertView.findViewById(R.id.txtAgentId);
        TextView txtAgtPhone = (TextView)convertView.findViewById(R.id.txtAgtPhone);
        TextView txtAgtEmail = (TextView) convertView.findViewById(R.id.txtAgtEmail);
        TextView txtAgtFirstName = (TextView) convertView.findViewById(R.id.txtAgtFirstName);
        TextView txtAgtLastName = (TextView) convertView.findViewById(R.id.txtAgtLastName);

     //   ivAgent.setImageResource(R.drawable.loginavatar);
        AgtViewImg.setImageResource(R.drawable.iggy);
        //txtAgentId.setText(agt.getAgentid().toString());
        txtAgtPhone.setText("Phone Number: " + AgtBusPhone);
        txtAgtEmail.setText("Email: " + AgtEmail);
        txtAgtFirstName.setText("First Name: " + AgtFirstName);
        txtAgtLastName.setText("Last Name: " + AgtLastName);


        return convertView;
    }
}
