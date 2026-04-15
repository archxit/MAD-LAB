package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    Button signupBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);
        db = new DBHelper(this);

        loginBtn.setOnClickListener(v -> {
            String u = username.getText().toString();
            String p = password.getText().toString();

            if(u.isEmpty() || p.isEmpty()){
                Toast.makeText(this, "Enter credentials", Toast.LENGTH_SHORT).show();
                return;
            }

            if(db.checkUser(u, p)){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, DashboardActivity.class));
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
        signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
        });
    }
}