package com.example.toastbuttontoggle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNormal;
    private ToggleButton toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNormal = findViewById(R.id.btnNormal);
        toggleBtn = findViewById(R.id.toggleBtn);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("APPLE");
            }
        });

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleBtn.isChecked()) {
                    showCustomToast("BANANA");
                } else {
                    showCustomToast("CAT");
                }
            }
        });
    }

    private void showCustomToast(String message) {
        // Inflate a simple custom layout for the toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_button, findViewById(android.R.id.content), false);

        // Replace the text inside the custom layout
        android.widget.TextView tv = layout.findViewById(R.id.tvToastText);
        tv.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
