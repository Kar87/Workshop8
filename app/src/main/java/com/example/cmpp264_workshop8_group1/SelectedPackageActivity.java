package com.example.cmpp264_workshop8_group1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;


public class SelectedPackageActivity extends AppCompatActivity {
    TextView tvSelectedName;
    TextView tvSelectedDesc;
    TextView tvSelectedPrice;
    TextView tvSelectedStartDate;
    TextView tvSelectedEndDate;
    Button btnBookPkg;
    PackageDB dataSource;
    BookingDB bookingSource;
    CustomerDB customerDB;
    Customer customer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_package);

        tvSelectedName = findViewById(R.id.tvSelectedName);
        tvSelectedDesc = findViewById(R.id.tvSelectedDesc);
        tvSelectedPrice = findViewById(R.id.tvSelectedPrice);
        tvSelectedStartDate = findViewById(R.id.tvSelectedStartDate);
        tvSelectedEndDate = findViewById(R.id.tvSelectedEndDate);

        btnBookPkg = findViewById(R.id.btnBookPkg);


        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        dataSource = new PackageDB(this);
        bookingSource = new BookingDB(this);
        customerDB = new CustomerDB(this);
        customer = customerDB.getCustomer(user, pwd);
        int custId = customer.getCustomerId();





        Package pkg = (Package) intent.getSerializableExtra("pkg");
        tvSelectedName.setText(pkg.getPkgName());
        tvSelectedDesc.setText(pkg.getPkgDesc());
        tvSelectedPrice.setText((pkg.getPkgBasePrice() + "$"));
        tvSelectedStartDate.setText(pkg.getPkgStartDate());
        tvSelectedEndDate.setText(pkg.getPkgEndDate());



        btnBookPkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SelectedPackageActivity.this);

                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Confirm Booking");
                alertBuilder.setMessage("Please confirm your booking");

                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int custId) {
//                    Booking booking = new Booking(0, tvSelectedName.getText().toString(), tvSelectedDesc.getText().toString(), tvSelectedPrice.getText().toString(), tvSelectedStartDate.getText().toString(), tvSelectedEndDate.getText().toString());
//                        bookingSource.insertBooking(booking);
                    }
                });
                alertBuilder.show();


            }
        });

    }
}
