package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;

public class BookingDetailActivity extends AppCompatActivity {
    EditText etBookingId, etBookingDate, etBookingNo, etTravelerCount, etCustomerId2, etTripTypeId, etPackageId2;
    Button btnPrintBooking;
    //Date date = Calender.getInstance().

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