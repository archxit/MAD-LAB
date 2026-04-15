package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button registerBtn, complaintBtn, pendingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        registerBtn = findViewById(R.id.registerBtn);
        complaintBtn = findViewById(R.id.complaintBtn);
        pendingBtn = findViewById(R.id.pendingBtn);

        registerBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Opening Register", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, RegisterActivity.class));
        });

        complaintBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Opening Complaint", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ComplaintActivity.class));
        });

        pendingBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Viewing Pending Complaints", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, PendingActivity.class));
        });
    }
}