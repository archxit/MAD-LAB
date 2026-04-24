package com.example.democode1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PendingActivity extends AppCompatActivity {

    ListView listView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);

        //👉 UI component to show list
        listView = findViewById(R.id.listView);
        //👉 connect to database
        db = new DBHelper(this);

        ArrayList<String> list = new ArrayList<>();

        Cursor c = db.getPending();

        //👉 goes row by row
        while(c.moveToNext()){
            //👉 column index: 0 → id  1 → date
            String id = c.getString(0);
            String date = c.getString(1);

            //👉 preparing display text
            list.add(id + " - " + date);
        }

        //👉 connects data → ListView
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        //👉 displays list on screen
        listView.setAdapter(adapter);
    }
}

//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: Show ALL data (not just pending)
//Cursor c = db.getAll();
//✅ Case 2: Show more fields
//String people = c.getString(2);
//
//list.add(id + " - " + date + " - " + people);
//✅ Case 3: Different table
//
//Just change DB query — rest SAME
//
//✅ Case 4: Filter different condition
//
//Example:
//
//WHERE type='Water'
//✅ Case 5: Different display format
//list.add("ID: " + id + "\nDate: " + date);