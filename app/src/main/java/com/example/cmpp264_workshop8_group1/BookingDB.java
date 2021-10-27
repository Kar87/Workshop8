package com.example.cmpp264_workshop8_group1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class BookingDB implements Serializable {
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;
    CustomerDB customerDB;
    Customer customer;



    public BookingDB(Context context) {
        this.context = context;
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

//    public long insertBooking(Booking booking) {
//        ContentValues cv = new ContentValues();
//        cv.put("BookingId", booking.getBookingId());
//        cv.put("BookingDate", booking.getBookingDate());
//        cv.put("BookingNo", booking.getBookingNo());
//        cv.put("TravelerCount", booking.getTravelerCount());
//        cv.put("CustomerId", booking.getCustomerId());
//        cv.put("TripTypeId", booking.getTripTypeId());
//        cv.put("PackageId", booking.getPackageId());
//        return db.insert("Bookings", null, cv);
//
//    }

    public void insertBooking(Booking booking) {
        ContentValues cv = new ContentValues();
        //cv.put("BookingId", booking.getBookingId());
        cv.put("BookingDate", booking.getBookingDate()); //returns current date - todays date (booking date)
        cv.put("BookingNo", booking.getBookingNo());
        cv.put("TravelerCount", booking.getTravelerCount());
        cv.put("CustomerId", booking.getCustomerId());
        cv.put("TripTypeId", booking.getTripTypeId());
        cv.put("PackageId", booking.getPackageId());
        //return db.insert("Bookings", null, cv);
    }
}
