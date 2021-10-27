package com.example.cmpp264_workshop8_group1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NAME = "TravelExpertsSqlLite.db";
    private static final int VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "CREATE TABLE \"Customers\" (\n" +
               "\t`CustomerId`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
               "\t`CustFirstName`\tVARCHAR(25) NOT NULL,\n" +
               "\t`CustLastName`\tVARCHAR(25) NOT NULL,\n" +
               "\t`CustAddress`\tVARCHAR(75) NOT NULL,\n" +
               "\t`CustCity`\tVARCHAR(50) NOT NULL,\n" +
               "\t`CustProv`\tVARCHAR(2) NOT NULL,\n" +
               "\t`CustPostal`\tVARCHAR(7) NOT NULL,\n" +
               "\t`CustCountry`\tVARCHAR(25),\n" +
               "\t`CustHomePhone`\tVARCHAR(20),\n" +
               "\t`CustBusPhone`\tVARCHAR(20) NOT NULL,\n" +
               "\t`CustEmail`\tVARCHAR(50) NOT NULL,\n" +
               "\t`AgentId`\tINT,\n" +
               "\t`UserName`\tVARCHAR(50) NOT NULL,\n" +
               "\t`Password`\tVARCHAR(50) NOT NULL\n" +
               ")";
       db.execSQL(sql);

       sql = "CREATE TABLE \"Packages\" (\n" +
               "\t`PackageId`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
               "\t`PkgName`\tVARCHAR(50) NOT NULL,\n" +
               "\t`PkgStartDate`\tDATETIME,\n" +
               "\t`PkgEndDate`\tDATETIME,\n" +
               "\t`PkgDesc`\tVARCHAR(50),\n" +
               "\t`PkgBasePrice`\tDECIMAL(19,4) NOT NULL,\n" +
               "\t`PkgAgencyCommission`\tDECIMAL(19,4)\n" +
               ")";
       db.execSQL(sql);
        sql = "insert into Packages (PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, " +
                "PkgAgencyCommission) " +
                "values ('Caribbean New Year', '20051225000000', '20060104000000', " +
                "'Cruise the Caribbean & Celebrate the New Year.', '4800', '400')";
        db.execSQL(sql);
        sql = "insert into Packages (PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, " +
                "PkgAgencyCommission) " +
                "values ('Polynesian Paradise', '20051212000000', '20051220000000', " +
                "'8 Day All Inclusive Hawaiian Vacation', '3000', '310')";
        db.execSQL(sql);
        sql = "insert into Packages (PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, " +
                "PkgAgencyCommission) " +
                "values ('Asian Expedition', '20060514000000', '20060528000000', " +
                "'Airfaire, Hotel and Eco Tour.', '2800', '300')";
        db.execSQL(sql);
        sql = "insert into Packages (PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, " +
                "PkgAgencyCommission) " +
                "values ('European Vacation', '20051101000000', '20051114000000', " +
                "'Euro Tour with Rail Pass and Travel Insurance', '3000', '280')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table Customers");
        db.execSQL("drop table Packages");
        onCreate(db);

    }
}
