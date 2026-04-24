package com.example.demoendsem2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatsActivity extends AppCompatActivity {

    TextView tvStats;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        tvStats = findViewById(R.id.tvStats);
        db = new DBHelper(this);

        Cursor c = db.getEvents();

        int total = 0;
        int max = 0;

        while(c.moveToNext()){
            //👉 column index 2 = points
            int p = c.getInt(2);
            //👉 sum of all points
            total += p;
            //👉 find highest value
            if(p > max){
                max = p;
            }
        }

        tvStats.setText("Total: " + total + "\nMax: " + max);

        if(max > 50){
            Toast.makeText(this, "Active User", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Regular User", Toast.LENGTH_SHORT).show();
        }
    }
}
//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: Only total needed
//tvStats.setText("Total: " + total);
//✅ Case 2: Average required
//int count = c.getCount();
//int avg = total / count;
//✅ Case 3: Minimum value
//int min = Integer.MAX_VALUE;
//
//if(p < min){
//    min = p;
//}
//✅ Case 4: Different condition
//if(total > 100)
//✅ Case 5: Different column
//
//If column changes:
//
//c.getInt(1)
//
//👉 depends on schema