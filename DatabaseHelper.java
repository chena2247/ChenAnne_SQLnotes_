package com.example.chena2247.mycontactapp_p1_attempt3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact-2018.db";
    public static final String TABLE_NAME = "Contact2018Table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "Name";
    //start of new stuff
    public static final String COLUMN_PHONE_CONTACT = "Phone";
    public static final String COLUMN_ADDRESS_CONTACT = "Address";
    //end new stuff

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + ", " + COLUMN_PHONE_CONTACT + ", " + COLUMN_ADDRESS_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        Log.d ("MyContactApp", "DatabaseHelper: constructed DatabaseHelper");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d ("MyContactApp", "DatabaseHelper: creating database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        Log.d ("MyContactApp", "DatabaseHelper: upgraded database");
        onCreate(db);
    }

    public boolean insertData(String name, String phone, String address) {
        Log.d ("MyContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);

        //new stuff
        contentValues.put(COLUMN_PHONE_CONTACT, phone);
        contentValues.put(COLUMN_ADDRESS_CONTACT, address);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Log.d ("MyContactApp", "DatabaseHelper: Contact insert - FAILED");
            return false;
        }
        else {
            Log.d ("MyContactApp", "DatabaseHelper: Contact insert - PASSED");
            return true;
        }

    }

    public Cursor getAllData() {
        Log.d("MyContactApp", "DatabaseHelper: calling getAllData method");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}

