package com.example.demoendsem2;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin, btnRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        db = new DBHelper(this);

        // REGISTER
        btnRegister.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            db.insertUser(user, pass);

            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
        });

        // LOGIN
        btnLogin.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if(db.checkUser(user, pass)){
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, EventFormActivity.class);
                startActivity(i);

            } else {
                Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: Only login (no register)
//
//👉 remove register button
//👉 skip insertUser
//
//✅ Case 2: Only register
//
//👉 just insertUser + toast
//
//✅ Case 3: No DB required
//
//👉 skip DB
//👉 directly:
//
//startActivity(...)
//✅ Case 4: Pass username forward
//Intent i = new Intent(this, EventFormActivity.class);
//i.putExtra("user", user);
//startActivity(i);
