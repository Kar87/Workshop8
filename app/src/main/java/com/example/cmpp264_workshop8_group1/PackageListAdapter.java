package com.example.cmpp264_workshop8_group1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;


public class PackageListAdapter extends ArrayAdapter<Package> {
    private static final String TAG = "PackageListAdapter";
    List<Package> pkgList = new ArrayList<>();
    private Context mContext;
    int mResource;

    /*
        Default constructor for the PackageListAdapater
        @param context
        @param resource
        @param objects
     */
    public PackageListAdapter(Context context, int resource, List<Package> pkgList){
        super(context, resource, pkgList);
        this.pkgList = pkgList;
        mContext = context;
        mResource = resource;

    }

    @Override
    public int getCount() {
        return pkgList.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get package information
        Integer PackageId = getItem(position).getPackageId();
        String PkgName = getItem(position).getPkgName();
        String PkgDesc = getItem(position).getPkgDesc();
        String PkgStartDate = getItem(position).getPkgStartDate();
        String PkgEndDate = getItem(position).getPkgEndDate();
        String PkgBasePrice = getItem(position).getPkgBasePrice();
        String PkgAgencyCommission = getItem(position).getPkgAgencyCommission();
        Log.d(TAG, "In data retrieval");




        //Create the package object with the information
        Package pkg = new Package(PackageId, PkgName, PkgDesc, PkgStartDate, PkgEndDate,PkgBasePrice,PkgAgencyCommission);

        Log.d(TAG, "In Package object");

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.layout_package_listview, null);
        Log.d(TAG, "In inflator method");

        //Instantiating text view items for data
        ImageView ivPackages = (ImageView) convertView.findViewById(R.id.ivPackage);
        TextView textPkgName = (TextView) convertView.findViewById(R.id.textPkgName);
        TextView textPkgDesc = (TextView) convertView.findViewById(R.id.textPkgDesc);
        Log.d(TAG, "In message instantiation");

        ivPackages.setImageResource(R.drawable.travelagentlogo2);
        textPkgName.setText(PkgName);
        textPkgDesc.setText(PkgDesc);
        Log.d(TAG, "In text appendage");

        return convertView;

    }
}
