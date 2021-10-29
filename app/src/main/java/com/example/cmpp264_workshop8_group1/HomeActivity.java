/**
 * Author: Nicolino Derilo and Sai Shalini Karaikatte Venugopal
 * Code for redirecting to required screens based on click of button
 */

package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    //reference to controls
    TextView tvHello;
    ImageView ivProfile, ivBookings,ivPackages, ivContactUs, ivWebsite, ivDetail;
    CustomerDB customerDB;
    //BookingDB bookingDB;
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
        ivDetail = findViewById(R.id.ivQuestion);
        tvHello = findViewById(R.id.tvHello);
        customerDB = new CustomerDB(this);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        Customer customer = customerDB.getCustomer(user, pwd);
        //greet user by name
        tvHello.setText("Hello " + customer.getCustFirstName());

        //re-direct to profile view with details of logged in user
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("password", pwd);
                intent.putExtra("mode", "update");
                startActivity(intent);
            }
        });

        //re-direct to travel experts website
        ivWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nicoderilo.tech/"));
                startActivity(browserIntent);
            }
        });

        //re-direct to  bookings activity
        ivBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("password", pwd);
                intent.putExtra("mode", "update");
                startActivity(intent);
            }
        });

        //re-direct to package activity
        ivPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PackageActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("password", pwd);
                startActivity(intent);
            }
        });

        //re-direct to announcements
       ivDetail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
               startActivity(intent);
           }
       });

       //re-direct to agent activity
        ivContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AgentActivity.class);
                startActivity(intent);
            }
        });
    }
}