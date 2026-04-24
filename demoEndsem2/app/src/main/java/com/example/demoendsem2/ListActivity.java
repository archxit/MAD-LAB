package com.example.demoendsem2;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    EditText etSearch;
    Button btnUpcoming, btnPast;

    DBHelper db;

    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        etSearch = findViewById(R.id.etSearch);
        btnUpcoming = findViewById(R.id.btnUpcoming);
        btnPast = findViewById(R.id.btnPast);

        db = new DBHelper(this);

        loadAllData();

        // 🔍 SEARCH
        etSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //👉 filters list in real-time
                adapter.getFilter().filter(s);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });

        // 📅 UPCOMING
        btnUpcoming.setOnClickListener(v -> {
            loadFilteredData(true);
        });

        // 📅 PAST
        btnPast.setOnClickListener(v -> {
            loadFilteredData(false);
        });
    }

    // 🔹 LOAD ALL EVENTS
    void loadAllData(){
        list = new ArrayList<>();

        Cursor c = db.getEvents();

        while(c.moveToNext()){
            String name = c.getString(0);
            String date = c.getString(1);

            list.add(name + " - " + date);
        }

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }

    // 🔹 FILTER DATA
    void loadFilteredData(boolean upcoming){

        list = new ArrayList<>();

        String today = "2026-04-25";

        Cursor c;

        if(upcoming){
            c = db.getReadableDatabase().rawQuery(
                    "SELECT * FROM events WHERE date > ?",
                    new String[]{today});
        } else {
            c = db.getReadableDatabase().rawQuery(
                    "SELECT * FROM events WHERE date < ?",
                    new String[]{today});
        }

        while(c.moveToNext()){
            String name = c.getString(0);
            String date = c.getString(1);

            list.add(name + " - " + date);
        }

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}


//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: Only ListView (no search/filter)
//
//👉 use only loadAllData()
//
//✅ Case 2: Filter by points
//WHERE points > 50
//✅ Case 3: Search by DB (advanced)
//
//👉 ignore — TextWatcher is enough
//
//✅ Case 4: No buttons given
//
//👉 skip filter part