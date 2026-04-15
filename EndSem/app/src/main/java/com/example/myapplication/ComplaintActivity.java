package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ComplaintActivity extends AppCompatActivity {

    EditText complaintId, complaintDate, people;
    Button calcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        complaintId = findViewById(R.id.complaintId);
        complaintDate = findViewById(R.id.complaintDate);
        people = findViewById(R.id.people);
        calcBtn = findViewById(R.id.calcBtn);

        calcBtn.setOnClickListener(v -> {
            String pStr = people.getText().toString();

            if(pStr.isEmpty()){
                Toast.makeText(this, "Enter number of people", Toast.LENGTH_SHORT).show();
                return;
            }

            int p = Integer.parseInt(pStr);

            Intent i = new Intent(this, RiskActivity.class);
            i.putExtra("people", p);
            startActivity(i);
        });
    }
}