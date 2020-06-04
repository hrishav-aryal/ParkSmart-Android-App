package com.example.ronin.parksmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ronin on 5/25/2017.
 */

public class databaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_CONTACTNO= "contactno";
    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_USERNAME= "username";
    private static final String COLUMN_EMAIL= "email";
    private static final String COLUMN_PASS= "pass";
    private static final String COLUMN_PLATE= "plate";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
    " contactno text not null, name text not null, username text not null, email text not null, pass text not null, plate text not null);";

    public databaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_CONTACTNO,c.getContactno());
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_USERNAME,c.getUsername());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASS,c.getPass());
        values.put(COLUMN_PLATE,c.getPlate());

        db.insert(TABLE_NAME, null, values);

        db.close();

    }

    public String searchPass(String username){
        db = this.getReadableDatabase();
        String query = "select username , pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String u , p;
        p = "NOT FOUND";
        if(cursor.moveToFirst())
        {
            do {
                u = cursor.getString(0);


                if(u.equals(username)){
                    p = cursor.getString(1);
                    break;
                }

            }while (cursor.moveToNext());
        }
        return p;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
