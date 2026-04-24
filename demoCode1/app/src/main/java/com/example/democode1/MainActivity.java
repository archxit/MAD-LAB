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

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //👉 Loads your XML UI
        setContentView(R.layout.activity_main);

        // connect UI to code
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            //👉 gets user input
            String user = username.getText().toString();
            String pass = password.getText().toString();

            // No validation needed (exam level)
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        });
    }
}





//🔄 HOW TO MODIFY IN EXAM
//Case 1: Question adds "Register button"
//
//👉 Add another button:
//
//registerBtn.setOnClickListener(v -> {
//    startActivity(new Intent(this, RegisterActivity.class));
//});

//Case 2: Wants validation (rare)
//if(user.equals("admin") && pass.equals("123")){
//    startActivity(new Intent(this, MenuActivity.class));
//} else {
//    Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
//}

//Case 3: Wants to pass username
//Intent i = new Intent(this, MenuActivity.class);
//i.putExtra("username", user);
//startActivity(i);