package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "complaints.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE complaints (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "type TEXT, " +
                "date TEXT, " +
                "resolved INTEGER)");

        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS complaints");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // ✅ Insert
    public void insertComplaint(String type, String date, boolean resolved){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("date", date);
        cv.put("resolved", resolved ? 1 : 0);
        db.insert("complaints", null, cv);
    }

    // ✅ Get Pending (unresolved)
    public Cursor getPending(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM complaints WHERE resolved = 0", null);
    }

    public void insertUser(String u, String p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", u);
        cv.put("password", p);
        db.insert("users", null, cv);
    }

    public boolean checkUser(String u, String p){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT * FROM users WHERE username=? AND password=?",
                new String[]{u, p}
        );

        boolean exists = c.getCount() > 0;
        c.close();
        return exists;
    }

}