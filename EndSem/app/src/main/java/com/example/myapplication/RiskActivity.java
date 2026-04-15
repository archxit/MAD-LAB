package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RiskActivity extends AppCompatActivity {

    TextView riskText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk);

        riskText = findViewById(R.id.riskText);

        int people = getIntent().getIntExtra("people", 0);

        int risk = (people * 5) + 10; // formula

        riskText.setText("Risk Amount: " + risk);
    }
}