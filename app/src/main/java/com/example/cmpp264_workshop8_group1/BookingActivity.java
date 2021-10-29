package com.example.cmpp264_workshop8_group1;
/**
 * Stan Abana
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {
    ListView lvBookings;
    DBHelper db;
    BookingDB bookingDB;
    CustomerDB customerDB;
    String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        lvBookings = findViewById(R.id.lvBookings);

        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        customerDB = new CustomerDB(this);
        bookingDB = new BookingDB(this);
        db = new DBHelper(this);
        Customer customer = customerDB.getCustomer(user, pwd);
        customerId = customer.getCustomerId().toString();
        loadData();

        lvBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), BookingDetailActivity.class);
                intent.putExtra("booking", (Booking) lvBookings.getAdapter().getItem(i));
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        BookingListAdapter adapter = new BookingListAdapter(BookingActivity.this, R.layout.layout_bookings_listview, bookingDB.getAllBookings(customerId));
        lvBookings.setAdapter(adapter);
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