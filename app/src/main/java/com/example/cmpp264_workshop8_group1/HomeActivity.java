package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    //reference to controls
    ImageView ivProfile, ivBookings,ivPackages, ivContactUs,ivWebsite, ivUnknown;

    @Override
    //application starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //assign value to the variables
        ivProfile = findViewById(R.id.ivProfile);
        ivBookings = findViewById(R.id.ivBookings);
        ivPackages = findViewById(R.id.ivPackages);
        ivContactUs = findViewById(R.id.ivContactUs);
        ivWebsite = findViewById(R.id.ivWebsite);

        Intent intent = getIntent();
        //String mode = intent.getStringExtra("mode");
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");

        //Links to edit profile
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("password",pwd);
                intent.putExtra("mode", "update");
                startActivity(intent);
            }
        });

        ivWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.travelagencygroup2.website/"));
                startActivity(browserIntent);
            }
        });

    }
}