package com.example.cmpp264_workshop8_group1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class BookingDB {
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;

    public BookingDB(Context context) {
        this.context = context;
        helper = new DBHelper(context);
        db = helper.getReadableDatabase();
    }

    public ArrayList<Booking> getAllBookings(String id) {
        ArrayList<Booking> list = new ArrayList<>();
        String [] columns = {"BookingId", "BookingDate","BookingNo", "TravelerCount", "CustomerId",
                "TripTypeId", "PackageId"};
        String cond = "CustomerId = ?";
        String [] args = {id + ""};
        Cursor cursor = db.query("Bookings", columns, cond , args, null, null, null);
        while (cursor.moveToNext())
        {
            list.add(new Booking(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6)));
        }
        return list;
    }

    public long insertBooking(Booking booking) {
        ContentValues cv = new ContentValues();
        //cv.put("BookingId", booking.getBookingId());
        cv.put("BookingDate", booking.getBookingDate());
        cv.put("BookingNo", booking.getBookingNo());
        cv.put("TravelerCount", booking.getTravelerCount());
        cv.put("CustomerId", booking.getCustomerId());
        cv.put("TripTypeId", booking.getTripTypeId());
        // cv.put("PackageId", booking.getPackageId());
        return db.insert("Bookings", null, cv);

    }
}
