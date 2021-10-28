package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingDetailActivity extends AppCompatActivity {
    EditText etBookingId, etBookingDate, etBookingNo, etTravelerCount, etCustomerId2, etTripTypeId, etPackageId2;
    Button btnPrintBooking;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        etBookingId = findViewById(R.id.etBookingId);
        etBookingDate = findViewById(R.id.etBookingDate);
        etBookingNo = findViewById(R.id.etBookingNo);
        etTravelerCount = findViewById(R.id.etTravelerCount);
        etCustomerId2 = findViewById(R.id.etCustomerId2);
        etTripTypeId = findViewById(R.id.etTripTypeId);
        etPackageId2 = findViewById(R.id.etPackageId2);
        btnPrintBooking = findViewById(R.id.btnPrintBooking);


        Booking booking = (Booking) getIntent().getSerializableExtra("booking");

        etBookingId.setText(booking.getBookingId() + "");
        etBookingDate.setText(booking.getBookingDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
        String sdfInString = etBookingDate.getText().toString();
        SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = sdf.parse(sdfInString);
            System.out.println(date);
            etBookingDate.setText(sdfOut.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        etBookingNo.setText(booking.getBookingNo());
        etTravelerCount.setText(booking.getTravelerCount() + "");
        etCustomerId2.setText(booking.getCustomerId() + "");
        etTripTypeId.setText(booking.getTripTypeId());
        etPackageId2.setText(booking.getPackageId() + "");

        btnPrintBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You've successfully printed your booking. Thank you.", Toast.LENGTH_LONG).show();
            }
        });
    }
}