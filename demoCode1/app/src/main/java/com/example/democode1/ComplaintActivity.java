package com.example.democode1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ComplaintActivity extends AppCompatActivity {

    EditText people;
    Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        people = findViewById(R.id.etPeople);
        calc = findViewById(R.id.btnCalc);

        calc.setOnClickListener(v -> {
            //👉 converts user input → integer
            int p = Integer.parseInt(people.getText().toString());

            //👉 this is exactly what question asked
            int risk = p * 5 + 10;

            Intent i = new Intent(this, ResultActivity.class);
            //👉 sending data to next screen
            i.putExtra("risk", risk);
            startActivity(i);
        });
    }
}

//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: Different formula
//
//If question says:
//
//risk = people * 10
//
//int risk = p * 10;
//✅ Case 2: Multiple inputs
//int a = Integer.parseInt(et1.getText().toString());
//int b = Integer.parseInt(et2.getText().toString());
//
//int result = a + b;
//✅ Case 3: Pass multiple values
//i.putExtra("risk", risk);
//i.putExtra("people", p);
//✅ Case 4: Different key name
//i.putExtra("value", risk);
//
/// / receive
//getIntent().getIntExtra("value", 0);
//
//👉 key must MATCH
//
//✅ Case 5: Show in Toast instead
//Toast.makeText(this, "Risk: " + risk, Toast.LENGTH_SHORT).show();