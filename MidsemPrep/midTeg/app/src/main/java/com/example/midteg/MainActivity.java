package com.example.midteg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggle;
    CheckBox cra, oe, lab, mess;
    RadioGroup genderGroup;
    Button confirm, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = findViewById(R.id.toggleStay);
        cra = findViewById(R.id.cbCra);
        oe = findViewById(R.id.cbOe);
        lab = findViewById(R.id.cbLab);
        mess = findViewById(R.id.cbMess);
        genderGroup = findViewById(R.id.rgGender);
        confirm = findViewById(R.id.btnConfirm);
        clear = findViewById(R.id.btnClear);

        // 🔥 Toggle Logic
        toggle.setOnCheckedChangeListener((b, isChecked) -> {
            mess.setChecked(isChecked);
        });

        // 🔥 CRA ↔ OE Logic
        cra.setOnCheckedChangeListener((b, checked) -> {
            oe.setEnabled(!checked);
        });

        oe.setOnCheckedChangeListener((b, checked) -> {
            cra.setEnabled(!checked);
        });

        // 🔥 Confirm Button
        confirm.setOnClickListener(v -> {

            String stay = toggle.isChecked() ? "On Campus" : "Off Campus";

            String options = "";
            if(cra.isChecked()) options += "CRA ";
            if(oe.isChecked()) options += "OE ";
            if(lab.isChecked()) options += "LAB ";
            if(mess.isChecked()) options += "MESS ";

            int id = genderGroup.getCheckedRadioButtonId();
            String gender = "Not Selected";
            if(id != -1){
                RadioButton rb = findViewById(id);
                if (rb != null) {
                    gender = rb.getText().toString();
                }
            }

            Toast.makeText(this, "Confirm clicked", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainActivity.this, ResultActivity.class);
            i.putExtra("stay", stay);
            i.putExtra("options", options);
            i.putExtra("gender", gender);
            startActivity(i);
        });

        // 🔥 Clear Button
        clear.setOnClickListener(v -> {
            toggle.setChecked(false);
            cra.setChecked(false);
            oe.setChecked(false);
            lab.setChecked(false);
            mess.setChecked(false);
            genderGroup.clearCheck();
        });
    }
}