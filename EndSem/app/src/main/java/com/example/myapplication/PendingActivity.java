package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PendingActivity extends AppCompatActivity {

    ListView listView;
    DBHelper db;
    Button goLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);

        goLoginBtn = findViewById(R.id.goLoginBtn);
        listView = findViewById(R.id.listView);
        db = new DBHelper(this);

        ArrayList<String> list = new ArrayList<>();

        Cursor c = db.getPending();

        if(c.getCount() == 0){
            Toast.makeText(this, "No Pending Complaints", Toast.LENGTH_SHORT).show();
            return;
        }

        while(c.moveToNext()){
            String item = "ID: " + c.getInt(0) +
                    "\nType: " + c.getString(1) +
                    "\nDate: " + c.getString(2);
            list.add(item);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        goLoginBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Going to Login Page", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finishAffinity();
        });

        listView.setAdapter(adapter);
    }
}