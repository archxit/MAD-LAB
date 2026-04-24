package com.example.demoendsem2;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class EventFormActivity extends AppCompatActivity {

    EditText name, date;
    SeekBar seekBar;
    TextView tvPoints;
    Button btnNext, btnUpdate, btnEdit;

    DBHelper db;

    int currentPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        name = findViewById(R.id.etEventName);
        date = findViewById(R.id.etDate);
        seekBar = findViewById(R.id.seekBar);
        tvPoints = findViewById(R.id.tvPoints);
        btnNext = findViewById(R.id.btnNext);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnEdit = findViewById(R.id.btnEdit);

        db = new DBHelper(this);

        // 🔹 DATE PICKER
        date.setOnClickListener(v -> {

            Calendar c = Calendar.getInstance();

            DatePickerDialog dp = new DatePickerDialog(this,
                    (view, y, m, d) -> {
                        date.setText(d + "/" + (m+1) + "/" + y);
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            );

            dp.show();
        });

        // 🔹 SEEKBAR
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentPoints = progress;
                tvPoints.setText("Points: " + progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 🔹 NEXT (INSERT DATA)
        btnNext.setOnClickListener(v -> {

            String n = name.getText().toString();
            String d = date.getText().toString();

            db.insertEvent(n, d, currentPoints);

            Toast.makeText(this, "Event Saved", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, ListActivity.class));
        });

        // 🔹 UPDATE
        btnUpdate.setOnClickListener(v -> {

            String n = name.getText().toString();
            String d = date.getText().toString();
            int p = seekBar.getProgress();

            db.updateEvent(n, d, p);

            Toast.makeText(this, "Event Updated", Toast.LENGTH_SHORT).show();
        });

        // 🔹 EDIT
        btnEdit.setOnClickListener(v -> {

            String n = name.getText().toString();

            Cursor c = db.getEventByName(n);

            if(c.moveToFirst()){
                String d = c.getString(1);
                int p = c.getInt(2);

                date.setText(d);
                seekBar.setProgress(p);

                Toast.makeText(this, "Loaded for editing", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Event not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}