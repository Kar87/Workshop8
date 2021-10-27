package com.example.cmpp264_workshop8_group1;

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

   /* public static int getCustomerId(String user, String pwd)
    {
        //String sql = "select * from Customers where Username=? and Password=?";
        String sql = "select CustomerId from Customers where Username=? and Password=?";
        String [] args = {user + "", pwd + ""};
        Cursor cursor = db.rawQuery(sql, args);
        cusId = cursor.getInt(0);
        //cursor.moveToNext();
        return cusId;

        *//*return new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8),
                cursor.getString(9), cursor.getString(10), cursor.getInt(11),
                cursor.getString(12), cursor.getString(13));*//*
    }*/

    /*public Customer getCustomer(String user, String pwd)
    {
        String sql = "select * from Customers where Username=? and Password=?";
        String [] args = {user + "", pwd + ""};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToNext();
        return new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8),
                cursor.getString(9), cursor.getString(10), cursor.getInt(11),
                cursor.getString(12), cursor.getString(13));
    }*/


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
                    cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6)));
        }
        return list;
    }

}
