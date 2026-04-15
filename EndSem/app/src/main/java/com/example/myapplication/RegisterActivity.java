package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText date, description;
    Spinner spinnerType;
    CheckBox checkResolved;
    Button saveBtn;
    DBHelper db;

    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        date = findViewById(R.id.date);
        description = findViewById(R.id.description);
        spinnerType = findViewById(R.id.spinnerType);
        checkResolved = findViewById(R.id.checkResolved);
        saveBtn = findViewById(R.id.saveBtn);

        db = new DBHelper(this);

        // Spinner data
        String[] types = {"Water Issue", "Electricity", "Road Damage"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, types);
        spinnerType.setAdapter(adapter);

        // Date picker
        date.setOnClickListener(v -> {

            Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show();
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp = new DatePickerDialog(this, (view, year, month, day) -> {
                selectedDate = day + "/" + (month+1) + "/" + year;
                date.setText(selectedDate);
            }, y, m, d);

            dp.show();
        });

        // Save button
        saveBtn.setOnClickListener(v -> {
            if(selectedDate.isEmpty()){
                Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show();
                return;
            }
            String type = spinnerType.getSelectedItem().toString();
            boolean resolved = checkResolved.isChecked();

            db.insertComplaint(type, selectedDate, resolved);

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });
    }
}