package com.example.cmpp264_workshop8_group1;

import static android.text.TextUtils.isEmpty;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;


public class SelectedPackageActivity extends AppCompatActivity {
    TextView tvSelectedName, tvSelectedDesc, tvSelectedPrice, tvSelectedStartDate, tvSelectedEndDate;
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

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date newDate = null;
//        try {
//            newDate = (Date) format.parse(tvSelectedStartDate.toString());
//            format = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
//            String date = format.format(newDate);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Package pkg = (Package) intent.getSerializableExtra("pkg");
        tvSelectedName.setText(pkg.getPkgName());
        tvSelectedDesc.setText(pkg.getPkgDesc());
        tvSelectedPrice.setText((pkg.getPkgBasePrice() + "$"));
        tvSelectedStartDate.setText(pkg.getPkgStartDate());
        Log.d("Aidan", pkg.getPkgStartDate());
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

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        tvSelectedStartDate.setText(simpleDateFormat.format(pkg.getPkgStartDate()));
//
//
//        DateFormat outputFormat = new SimpleDateFormat("MM/yyyy", Locale.CANADA);
//        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
//
//        String inputText = tvSelectedStartDate.getText().toString();
//        Date date = null;
//        try {
//            date = inputFormat.parse(inputText);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String outputText = outputFormat.format(date);
//        tvSelectedStartDate.setText(outputText);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        if(!isEmpty(tvSelectedStartDate.getText()))
//        {
//            if(tvSelectedStartDate.getText().equals("\\d{4}-\\d{2}-\\d{2}(?:(?:\\s([0-1]\\d|[2][0-3])\\:([0-5]\\d)(?::([0-5]\\d))?)?)"))
//            {
//                try {
//                    if(sdf.parse(String.valueOf(LocalDate.now())).compareTo(sdf.parse(tvSelectedStartDate.getText().substring(0,10))) > 0) {
//                        errors.append("- Start date cannot be in the past.\n");
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        //20051225000000
        SimpleDateFormat sdformatter = new SimpleDateFormat("yyyyMMddkkmmss");
        String sddateInString = tvSelectedStartDate.getText().toString();
        SimpleDateFormat sdformatterOut = new SimpleDateFormat("dd MMM yyyy");
        try {

            Date date = sdformatter.parse(sddateInString);
            System.out.println(date);
            tvSelectedStartDate.setText(sdformatterOut.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat edformatter = new SimpleDateFormat("yyyyMMddkkmmss");
        String eddateInString = tvSelectedEndDate.getText().toString();
        SimpleDateFormat edformatterOut = new SimpleDateFormat("dd MMM yyyy");
        try {

            Date date = edformatter.parse(eddateInString);
            System.out.println(date);
            tvSelectedEndDate.setText(edformatterOut.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }




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
                    public void onClick(DialogInterface dialogInterface, int position) {
                        Booking booking = new Booking(0, currentDate.toString(), "ABCE12345",
                                spNumOfTrav.getSelectedItem().toString(),
                                customer.getCustomerId(), spTripId.getSelectedItem().toString(), 0);
                        bookingDB.insertBooking(booking);
                        Intent intent = new Intent(SelectedPackageActivity.this, HomeActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("password", pwd);
                        startActivity(intent);
                    }
                });
                alertBuilder.show();
            }
        });

    }

//    private boolean Validator() {
//        StringBuilder errors = new StringBuilder();
//
//
//        }
//        if (errors.length() > 0) {
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(SelectedPackageActivity.this);
//            builder1.setMessage(errors);
//            builder1.setCancelable(true);
//
//            builder1.setPositiveButton(
//                    "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });
//
//            AlertDialog alert11 = builder1.create();
//            alert11.show();
//            return false;
//        }
//        // No errors
//        return true;
//    }
}
