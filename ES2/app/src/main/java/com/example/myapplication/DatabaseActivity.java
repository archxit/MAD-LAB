package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {

    DBHelper db;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        db = new DBHelper(this);
        tvData = findViewById(R.id.tvData);

        String name = getIntent().getStringExtra("name");
        int tickets = getIntent().getIntExtra("tickets", 0);
        String transport = getIntent().getStringExtra("transport");
        String seat = getIntent().getStringExtra("seat");
        int total = getIntent().getIntExtra("total", 0);

        findViewById(R.id.btnInsert).setOnClickListener(v ->
                db.insertData(name, tickets, transport, seat, total)
        );

        findViewById(R.id.btnView).setOnClickListener(v -> {
            Cursor c = db.getData();
            StringBuilder sb = new StringBuilder();

            while (c.moveToNext()) {
                sb.append("ID: ").append(c.getInt(0)).append("\n")
                        .append("Name: ").append(c.getString(1)).append("\n")
                        .append("Tickets: ").append(c.getInt(2)).append("\n")
                        .append("Transport: ").append(c.getString(3)).append("\n")
                        .append("Seat: ").append(c.getString(4)).append("\n")
                        .append("Total: ₹").append(c.getInt(5)).append("\n\n");
            }

            tvData.setText(sb.toString());
        });

        findViewById(R.id.btnUpdate).setOnClickListener(v ->
                db.updateData(1, "Updated Name")
        );

        findViewById(R.id.btnDelete).setOnClickListener(v ->
                db.deleteData(1)
        );
    }
}