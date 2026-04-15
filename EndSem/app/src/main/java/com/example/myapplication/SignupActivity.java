package com.example.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText newUser, newPass;
    Button registerBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        newUser = findViewById(R.id.newUser);
        newPass = findViewById(R.id.newPass);
        registerBtn = findViewById(R.id.registerBtn);
        db = new DBHelper(this);

        registerBtn.setOnClickListener(v -> {
            String u = newUser.getText().toString();
            String p = newPass.getText().toString();

            if(u.isEmpty() || p.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            db.insertUser(u, p);
            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}