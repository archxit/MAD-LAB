package com.example.democode1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    Button btnRegister, btnComplaint, btnPending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnRegister = findViewById(R.id.btnRegister);
        btnComplaint = findViewById(R.id.btnComplaint);
        btnPending = findViewById(R.id.btnPending);

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        btnComplaint.setOnClickListener(v -> {
            startActivity(new Intent(this, ComplaintActivity.class));
        });

        btnPending.setOnClickListener(v -> {
            startActivity(new Intent(this, PendingActivity.class));
        });
    }
}


//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: More buttons (very common)
//
//Just copy-paste pattern:
//
//btnNew.setOnClickListener(v -> {
//    startActivity(new Intent(this, NewActivity.class));
//});
//✅ Case 2: Different screen names
//
//If question says:
//
//"Event screen"
//"Report screen"
//
//👉 Just change:
//
//new Intent(this, EventActivity.class)
//✅ Case 3: Pass data (rare but possible)
//Intent i = new Intent(this, RegisterActivity.class);
//i.putExtra("type", "complaint");
//startActivity(i);
//✅ Case 4: Only 2 buttons instead of 3
//
//👉 Just remove one — nothing else changes