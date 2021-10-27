package com.example.cmpp264_workshop8_group1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class PackageActivity extends AppCompatActivity {
    ListView lvPackage;
    PackageDB dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        lvPackage = findViewById(R.id.lvPackage);
        dataSource = new PackageDB(this);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");




        loadData();
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
        ArrayAdapter<Package> adapter = new ArrayAdapter<Package>(this, android.R.layout.simple_list_item_1, dataSource.getAllPackages());
        lvPackage.setAdapter(adapter);
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
