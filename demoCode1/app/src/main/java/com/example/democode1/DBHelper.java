package com.example.democode1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//    CREATE TABLE → columns
//    INSERT → ContentValues
//    FETCH → rawQuery
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ComplaintDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //👉 This runs ONLY first time app opens
        db.execSQL("CREATE TABLE IF NOT EXISTS complaints(id TEXT, date TEXT, people INTEGER, status TEXT, type TEXT)");    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //👉 Used when version changes (ignore in exam)
        db.execSQL("DROP TABLE IF EXISTS complaints");
        onCreate(db);
    }

    public void insertData(String id, String date, int people, String status, String type){
        //            If columns change, 👉 Just change cv.put
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("date", date);
        cv.put("people", people);
        cv.put("status", status);
        cv.put("type", type);
        db.insert("complaints", null, cv);
    }

    public Cursor getPending(){
        SQLiteDatabase db = this.getReadableDatabase();
        //👉 This fetches ONLY pending rows
        return db.rawQuery("SELECT * FROM complaints WHERE status='pending'", null);
    }
}

