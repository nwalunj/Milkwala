package com.nathdev.milkwala.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CustDataBaseAdapter {

    public static final int NAME_COLUMN = 1;
    static final String DATABASE_NAME = "cust.db";
    private static final String TABLE_NAME = "CUSTOMER";
    static final int DATABASE_VERSION = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + TABLE_NAME +
            "( ID" + " integer primary key autoincrement," + "customerName  text,email text,phone text,address text,milkqty integer); ";
    // Context of the application using the database.
    private final Context context;
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Database open/upgrade helper
    private CustomerDataBaseHelper dbHelper;

    public CustDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new CustomerDataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public CustDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public Integer insertEntry(String customerName, String email,String phone,String address,String milkqty) {
        ContentValues newValues = new ContentValues();

        // Assign values for each row.
        newValues.put("customerName", customerName);
        newValues.put("email", email);
        newValues.put("phone", phone);
        newValues.put("address", address);
        Integer milkqtyint= Integer.parseInt(milkqty);
        newValues.put("milkqty", milkqty);

        // Insert the row into your table
        db.insert(TABLE_NAME, null, newValues);

        Cursor cursor = db.query(TABLE_NAME, null, "customerName=?", new String[]{customerName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        Integer customerID = cursor.getInt(cursor.getColumnIndex("ID"));
       System.out.print(customerID);
        return customerID;

    }

    public int deleteEntry(String customerName) {
        //String id=String.valueOf(ID);
        String where = "customerName=?";
        int numberOFEntriesDeleted = db.delete("CUSTOMER", where, new String[]{customerName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String customerName) {
        Cursor cursor = db.query("CUSTOMER", null, " customerName=?", new String[]{customerName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        StringBuilder sb= new StringBuilder();
        sb.append(customerName);sb.append("/");
        String email = cursor.getString(cursor.getColumnIndex("email"));
        sb.append(email);sb.append("/");
        String phone = cursor.getString(cursor.getColumnIndex("phone"));
        sb.append(phone);sb.append("/");
        String address = cursor.getString(cursor.getColumnIndex("address"));
        sb.append(address);sb.append("/");
        String milkqty = cursor.getString(cursor.getColumnIndex("milkqty"));
        sb.append(milkqty);

        cursor.close();
        return sb.toString();
    }

    public void updateEntry(String customerName, String email,String phone,String address,String milkqty) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("customerName", customerName);
        updatedValues.put("email", email);
        updatedValues.put("phone", phone);
        updatedValues.put("address", address);
        updatedValues.put("milkqty", milkqty);

        String where = "customerName = ?";
        db.update("CUSTOMER", updatedValues, where, new String[]{customerName});
    }
}

