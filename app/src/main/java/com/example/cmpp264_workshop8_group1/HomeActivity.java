package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    //reference to controls
    TextView tvHello;
    ImageView ivProfile, ivBookings,ivPackages, ivContactUs;
    CustomerDB customerDB;
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
        tvHello = findViewById(R.id.tvHello);
        customerDB = new CustomerDB(this);
        Intent intent = getIntent();
        //String mode = intent.getStringExtra("mode");
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        Customer customer = customerDB.getCustomer(user,pwd);
        tvHello.setText("Hello "+ customer.getCustFirstName());

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("password",pwd);
                intent.putExtra("mode", "update");
                startActivity(intent);

                /*Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.putExtra("mode", "insert");
                //LoginActivity.etUsername1.getText();
                startActivity(intent);*/
            }
        });
    }
}