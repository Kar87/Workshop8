package com.example.cmpp264_workshop8_group1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageActivity extends AppCompatActivity {
    ListView lvPackage;
    PackageDB dataSource;
    private static final String TAG = "PackageActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        Log.d(TAG, "onCreate: Started.");
        ListView lvPackage = findViewById(R.id.lvPackage);
        dataSource = new PackageDB(this);
        ArrayList<Package> packageList = new ArrayList<>();

        //Intent and password/user passing for data structuring.
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");


        PackageListAdapter adapter = new PackageListAdapter(this, R.layout.layout_package_listview, dataSource.getAllPackages());
        lvPackage.setAdapter(adapter);
       // loadData();
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
    private void loadData(){
        //Calling PackageAdapter to populate listview with layout.xml file

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}




