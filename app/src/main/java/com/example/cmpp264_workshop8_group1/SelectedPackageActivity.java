package com.example.cmpp264_workshop8_group1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;


public class SelectedPackageActivity extends AppCompatActivity {
    TextView tvSelectedName, tvSelectedDesc, tvSelectedPrice, tvSelectedStartDate, tvSelectedEndDate;
    EditText etBookingNo;
    Button btnBookPkg;
    Spinner spTripId, spNumOfTrav;
    PackageDB dataSource;
    BookingDB bookingDB;
    CustomerDB customerDB;
    Customer customer;
    Booking bookingData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_package);


        tvSelectedName = findViewById(R.id.tvSelectedName);
        tvSelectedDesc = findViewById(R.id.tvSelectedDesc);
        tvSelectedPrice = findViewById(R.id.tvSelectedPrice);
        tvSelectedStartDate = findViewById(R.id.tvSelectedStartDate);
        tvSelectedEndDate = findViewById(R.id.tvSelectedEndDate);
        spNumOfTrav = findViewById(R.id.spNumOfTrav);
        spTripId = findViewById(R.id.spTripId);
        etBookingNo = findViewById(R.id.etBookingNo);

        btnBookPkg = findViewById(R.id.btnBookPkg);


        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        dataSource = new PackageDB(this);
        bookingDB = new BookingDB(this);
        customerDB = new CustomerDB(this);
        customer = customerDB.getCustomer(user, pwd);
        int custId = customer.getCustomerId();
        boolean bookingInserted;


        Package pkg = (Package) intent.getSerializableExtra("pkg");
        tvSelectedName.setText(pkg.getPkgName());
        tvSelectedDesc.setText(pkg.getPkgDesc());
        tvSelectedPrice.setText((pkg.getPkgBasePrice() + "$"));
        tvSelectedStartDate.setText(pkg.getPkgStartDate());
        tvSelectedEndDate.setText(pkg.getPkgEndDate());
        //Create data for drop down lists(Spinners)

        //Trip type ID spinner
        String[] tripId = new String[]{"G", "L", "B"};
        ArrayAdapter<String> tripAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tripId);
        spTripId.setAdapter(tripAdapter);

        //No. of travelers spinner
        String[] travelers = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> travelerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, travelers);
        spNumOfTrav.setAdapter(travelerAdapter);

        //Get today's date for bookingDate
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());


        btnBookPkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = Validator();
                if (valid) {
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
                        public void onClick(DialogInterface dialogInterface, int position) {
                            Booking booking = new Booking(0, currentDate.toString(), etBookingNo.getText().toString(),
                                    spNumOfTrav.getSelectedItem().toString(),
                                    customer.getCustomerId(), spTripId.getSelectedItem().toString(), 0);
                            bookingDB.insertBooking(booking);

                        }
                    });
                    alertBuilder.show();
                }
            }
        });

    }

    private boolean Validator() {
        StringBuilder errors = new StringBuilder();
        if (etBookingNo.getText().toString().matches("")) {
            errors.append("- Please enter a Booking Number.\n");

        }
        if (errors.length() > 0) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(SelectedPackageActivity.this);
            builder1.setMessage(errors);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }
        // No errors
        return true;
    }
}
