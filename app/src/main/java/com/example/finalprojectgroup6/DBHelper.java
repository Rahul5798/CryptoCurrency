package com.example.finalprojectgroup6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //Database name and version
    static final String DBNAME = "FinalProject.db";
    static final int VERSION = 1;

    //Declaring and Initializing the database schema
    //Table 1 - User table
    static final String TABLE1_NAME = "User";
    static final String T1_COL1 = "UserId";
    static final String T1_COL2 = "Name";
    static final String T1_COL3 = "Email";
    static final String T1_COL4 = "Password";

    static final String CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + "("
            + T1_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T1_COL2 + " TEXT NOT NULL,"
            + T1_COL3 + " TEXT NOT NULL,"
            + T1_COL4 + " TEXT NOT NULL);";
    static final String DROP_TABLE1 = "DROP TABLE IF EXISTS " + TABLE1_NAME;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the table in the database
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE1);
        onCreate(db);
    }

    //method for inserting data into user table
    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(T1_COL2, user.getName());
        cv.put(T1_COL3, user.getEmail());
        cv.put(T1_COL4, user.getPassword());

        long result = db.insert(TABLE1_NAME, null, cv);
        return ((result == -1) ? false : true);
    }

    //method for reading data for login
    public  Cursor findUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new  User();
        user.setEmail(email);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + T1_COL3 + " = '" + email + "' ",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
