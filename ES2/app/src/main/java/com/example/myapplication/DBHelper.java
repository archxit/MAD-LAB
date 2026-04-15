package com.example.myapplication;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "TravelDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE bookings(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "tickets INTEGER," +
                "transport TEXT," +
                "seat TEXT," +
                "price INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS bookings");
        onCreate(db);
    }

    public void insertData(String name, int tickets, String transport, String seat, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("tickets", tickets);
        cv.put("transport", transport);
        cv.put("seat", seat);
        cv.put("price", price);

        db.insert("bookings", null, cv);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM bookings", null);
    }

    public void updateData(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);

        db.update("bookings", cv, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("bookings", "id=?", new String[]{String.valueOf(id)});
    }
}