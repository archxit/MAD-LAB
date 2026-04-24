package com.example.demoendsem2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "EventDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // user table
        db.execSQL("CREATE TABLE IF NOT EXISTS users(username TEXT, password TEXT)");

        // events table
        db.execSQL("CREATE TABLE IF NOT EXISTS events(name TEXT, date TEXT, points INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS events");
        onCreate(db);
    }

    // insert event
    public void insertEvent(String name, String date, int points){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("date", date);
        cv.put("points", points);
        db.insert("events", null, cv);
    }

    public void insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        db.insert("users", null, cv);
    }
    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM users WHERE username=? AND password=?",
                new String[]{username, password}
        );

        return c.getCount() > 0;
    }

    public void updatePoints(String name, int points){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("points", points);

        db.update("events", cv, "name=?", new String[]{name});
    }

    public void updateEvent(String name, String date, int points){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("points", points);

        db.update("events", cv, "name=?", new String[]{name});
    }

    public Cursor getEventByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM events WHERE name=?", new String[]{name});
    }

    // get all events
    public Cursor getEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM events", null);
    }
}