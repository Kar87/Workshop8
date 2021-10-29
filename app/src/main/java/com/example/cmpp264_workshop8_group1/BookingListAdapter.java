package com.example.cmpp264_workshop8_group1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
Author: Aidan Goguen
Adapter class that adds display for listview in Bookings
 */

public class BookingListAdapter extends ArrayAdapter<Booking> {
    private Context bContext;
    private int bResource;
    List<Booking> bkList = new ArrayList<>();



    public BookingListAdapter(Context context, int resource, List<Booking> bkList){
        super(context, resource, bkList);
        this.bkList = bkList;
        bContext = context;
        bResource = resource;
    }

    @Override
    public int getCount() {
        return bkList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer BookingId = getItem(position).getBookingId();
        String BookingDate = getItem(position).getBookingDate();
        String BookingNo = getItem(position).getBookingNo();
        String TravelerCount = getItem(position).getTravelerCount();
        Integer CustomerId = getItem(position).getCustomerId();
        String TripTypeId = getItem(position).getTripTypeId();
        Integer PackageId = getItem(position).getPackageId();


        Booking booking = new Booking(BookingId, BookingDate, BookingNo, TravelerCount, CustomerId, TripTypeId, PackageId);

        LayoutInflater inflater = LayoutInflater.from(bContext);
        convertView = inflater.inflate(R.layout.layout_bookings_listview, null);

        ImageView BookingViewImg = (ImageView) convertView.findViewById(R.id.BookingViewImg);
        //ImageView ivPackages = (ImageView) convertView.findViewById(R.id.ivAgent);
        TextView txtBookingNo = (TextView) convertView.findViewById(R.id.txtBookingNo);
        TextView txtPkgId = (TextView) convertView.findViewById(R.id.txtPkgId);

        BookingViewImg.setImageResource(R.drawable.bookings);
        txtBookingNo.setText("Booking Number: " + BookingNo);
        txtPkgId.setText("Click to see Booking details");

        return convertView;
    }
}
