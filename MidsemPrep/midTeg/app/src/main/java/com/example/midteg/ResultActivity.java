package com.example.midteg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class ResultActivity extends AppCompatActivity {

    TextView tv;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv = findViewById(R.id.tvResult);
        back = findViewById(R.id.btnBack);

        Intent i = getIntent();

        String result = "Stay: " + i.getStringExtra("stay") +
                "\nOptions: " + i.getStringExtra("options") +
                "\nGender: " + i.getStringExtra("gender");

        tv.setText(result);

        back.setOnClickListener(v -> finish());
    }

    // 🔥 MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.date){
            Calendar c = Calendar.getInstance();

            new DatePickerDialog(this, (view, y, m, d) -> {
                Toast.makeText(this, d+"/"+(m+1)+"/"+y, Toast.LENGTH_SHORT).show();
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        }
        return true;
    }
}
