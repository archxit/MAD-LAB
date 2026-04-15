package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etTickets;
    Spinner spTransport;
    RadioGroup rgSeat;
    Button btnSubmit;

    String[] transportOptions = {"Bus", "Train", "Car"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etTickets = findViewById(R.id.etTickets);
        spTransport = findViewById(R.id.spTransport);
        rgSeat = findViewById(R.id.rgSeat);
        btnSubmit = findViewById(R.id.btnSubmit);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                transportOptions);
        spTransport.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString();
            int tickets = Integer.parseInt(etTickets.getText().toString());
            String transport = spTransport.getSelectedItem().toString();

            int selectedId = rgSeat.getCheckedRadioButtonId();
            RadioButton rb = findViewById(selectedId);
            String seat = rb.getText().toString();

            int price = 0;
            switch (transport) {
                case "Bus": price = 500; break;
                case "Train": price = 800; break;
                case "Car": price = 1000; break;
            }

            int total = tickets * price;

            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("tickets", tickets);
            intent.putExtra("transport", transport);
            intent.putExtra("seat", seat);
            intent.putExtra("total", total);

            startActivity(intent);
        });
    }
}