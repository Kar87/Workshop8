package com.example.cmpp264_workshop8_group1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PackageDB {
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;

    /**
     * Method PackageDB which accepts context as parameter
     *
     * @param context
     */
    public PackageDB(Context context) {
        this.context = context;
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();


    }

    public Package getPackage(int pkgId) {
        String sql = "SELECT * FROM Packages";
        String[] args = {pkgId + ""};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToNext();
        return new Package((cursor.getInt(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
    }

    public Package getPackagesById(int PkgId) {
        String sql = "SELECT * FROM Packages WHERE PkgId=?";
        String[] args = {PkgId + ""};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToNext();
        return new Package(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6));
    }

    public ArrayList<Package> GetPackageForListView() {
        ArrayList<Package> pkgList = new ArrayList<>();
        String[] columns = {"PkgName", "PkgDesc"};
        Cursor cursor = db.query("Packages", columns, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            pkgList.add(new Package(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
        }
        return pkgList;
    }

    public ArrayList<Package> getAllPackages() {
        ArrayList<Package> pkgList = new ArrayList<>();
        String[] columns = {"PackageId", "PkgName", "PkgStartDate", "PkgEndDate", "PkgDesc", "PkgBasePrice", "PkgAgencyCommission"};
        Cursor cursor = db.query("Packages", columns, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            pkgList.add(new Package(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
        }
        return pkgList;
    }
}