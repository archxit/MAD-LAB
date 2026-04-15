package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    TextView tvSummary;
    Button btnEdit, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);
        btnEdit = findViewById(R.id.btnEdit);
        btnNext = findViewById(R.id.btnNext);

        Intent i = getIntent();

        String name = i.getStringExtra("name");
        int tickets = i.getIntExtra("tickets", 0);
        String transport = i.getStringExtra("transport");
        String seat = i.getStringExtra("seat");
        int total = i.getIntExtra("total", 0);

        tvSummary.setText(
                "Name: " + name +
                        "\nTickets: " + tickets +
                        "\nTransport: " + transport +
                        "\nSeat: " + seat +
                        "\nTotal: ₹" + total
        );

        btnEdit.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(this, DatabaseActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("tickets", tickets);
            intent.putExtra("transport", transport);
            intent.putExtra("seat", seat);
            intent.putExtra("total", total);
            startActivity(intent);
        });
    }
}
