package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
        //String mode = intent.getStringExtra("mode");
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        Customer customer = customerDB.getCustomer(user, pwd);
        tvHello.setText("Hello " + customer.getCustFirstName());

        /**
         * Get customer id from customers table
         */
        /*customerDB = new CustomerDB(this);
        intent = getIntent();
        user = intent.getStringExtra("user");
        pwd = intent.getStringExtra("password");
        customer = customerDB.getCustomer(user,pwd);
        int custId = customer.getCustomerId();*/

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("password", pwd);
                intent.putExtra("mode", "update");
                startActivity(intent);

                /*Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.putExtra("mode", "insert");
                //LoginActivity.etUsername1.getText();
                startActivity(intent);*/
            }
        });

        ivWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nicoderilo.tech/"));
                startActivity(browserIntent);
            }
        });

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

        ivPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PackageActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("password", pwd);
                startActivity(intent);
            }
        });

       ivDetail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
               startActivity(intent);
           }
       });

        ivContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AgentActivity.class);
                startActivity(intent);
            }
        });
    }
}