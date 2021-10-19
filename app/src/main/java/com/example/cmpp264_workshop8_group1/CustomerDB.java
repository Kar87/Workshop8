package com.example.cmpp264_workshop8_group1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CustomerDB {
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;

    /**
     * Method CustomerDB which accepts context as parameter
     * @param context
     */
    public CustomerDB(Context context) {
        this.context = context;
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public boolean checkUserName(String userName) {
        String sql = "select * from Customers where UserName=?";
        String [] args = {userName + ""};
        Cursor cursor = db.rawQuery(sql, args);
        if(cursor.getCount() > 0)
            return true;
        return false;
    }

    public Boolean checkUserNamePassword(String user, String pwd) {
        String sql = "select * from Customers where Username=? and Password=?";
        String [] args = {user + "", pwd + ""};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToNext();
        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }



    /**
     * Method getCustomer() that returns the details of current customer
     * @return
     */
    public Customer getCustomer(String user, String pwd)
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
    }

    /**
     * Method updateCustomer which accepts parameter of type Customer
     * Returns update statement to update the table with values which are changed
     * @param customer
     * @return
     */
    public int updateCustomer(Customer customer) {
        ContentValues cv = new ContentValues();
        cv.put("CustFirstName", customer.getCustFirstName());
        cv.put("CustLastName", customer.getCustLastName());
        cv.put("CustAddress", customer.getCustAddress());
        cv.put("CustCity", customer.getCustCity());
        cv.put("CustProv", customer.getCustProv());
        cv.put("CustPostal", customer.getCustPostal());
        cv.put("CustCountry", customer.getCustCountry());
        cv.put("CustHomePhone", customer.getCustHomePhone());
        cv.put("CustBusPhone", customer.getCustBusPhone());
        cv.put("CustEmail", customer.getCustEmail());
        cv.put("AgentId", customer.getAgentId());
        cv.put("UserName", customer.getUserName());
        cv.put("Password", customer.getPassword());
        String [] args = { customer.getCustomerId() + ""};
        String where = "CustomerId=?";
        return db.update("Customers", cv, where, args);
    }

    /**
     * Method insertCustomer which accepts parameter of type Customer
     * Returns update statement to insert the table with values that were added
     * @param customer
     * @return
     */
    public long insertCustomer(Customer customer) {
        ContentValues cv = new ContentValues();
        cv.put("CustFirstName", customer.getCustFirstName());
        cv.put("CustLastName", customer.getCustLastName());
        cv.put("CustAddress", customer.getCustAddress());
        cv.put("CustCity", customer.getCustCity());
        cv.put("CustProv", customer.getCustProv());
        cv.put("CustPostal", customer.getCustPostal());
        cv.put("CustCountry", customer.getCustCountry());
        cv.put("CustHomePhone", customer.getCustHomePhone());
        cv.put("CustBusPhone", customer.getCustBusPhone());
        cv.put("CustEmail", customer.getCustEmail());
        cv.put("AgentId", customer.getAgentId());
        cv.put("UserName", customer.getUserName());
        cv.put("Password", customer.getPassword());
        return db.insert("Customers", null, cv);
    }

    /**
     * Method deleteCustomer which accepts customerId as parameter
     * Returns delete statement to delete the customer with the particular customerId
     * @param customerId
     * @return
     */
    public int deleteCustomer(int customerId) {
        String [] args = { customerId + "" };
        String where = "CustomerId = ?";
        return db.delete("Customers", where, args);
    }
}
