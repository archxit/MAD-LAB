package com.example.democode1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinner;
    CheckBox checkBox;
    Button save;

    DBHelper db;

    EditText etDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.spinnerType);
        checkBox = findViewById(R.id.checkResolved);
        save = findViewById(R.id.btnSave);
        etDate = findViewById(R.id.etDate);

        db = new DBHelper(this);

        // Spinner data (very important)
        String[] items = {"Water", "Electricity", "Road"};

        //👉 connects data → spinner UI
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);

        save.setOnClickListener(v -> {

            //👉 user selected complaint type
            String type = spinner.getSelectedItem().toString();

            String status = checkBox.isChecked() ? "resolved" : "pending";

            String id = "C" + System.currentTimeMillis();
            String date = "2026-04-25";

            db.insertData(id, date, 0, status, type);

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });


//        etDate.setOnClickListener(v -> {
//
//            Calendar c = Calendar.getInstance();
//
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog dp = new DatePickerDialog(
//                    RegisterActivity.this,
//                    (view, y, m, d) -> {
//                        String date = d + "/" + (m + 1) + "/" + y;
//                        etDate.setText(date);
//                    },
//                    year, month, day
//            );
//
//            dp.show();
        //🔄 HOW TO MODIFY IN EXAM
        //✅ Case 1: Only date needed
        //
        //👉 use as-is
        //
        //✅ Case 2: Date + time
        //
        //👉 ignore time unless explicitly asked (too long)
        //
        //✅ Case 3: Store in DB
        //String date = etDate.getText().toString();
        //⚠️ COMMON MISTAKES
        //
        //❌ forgetting (m+1) → month wrong
        //❌ not setting focusable=false
        //❌ not calling .show()
//        });


    }
}


//🔄 HOW TO MODIFY IN EXAM
//✅ Case 1: Different fields
//
//If question says:
//
//event name
//points
//
//👉 Replace:
//
//cv.put("name", name);
//cv.put("points", points);
//✅ Case 2: No Spinner
//
//👉 remove spinner completely
//👉 use EditText instead
//
//✅ Case 3: Multiple checkboxes
//if(cb1.isChecked()) { ... }
//if(cb2.isChecked()) { ... }
//✅ Case 4: Want actual date
//
//(only if needed)
//
//Calendar c = Calendar.getInstance();
//String date = c.getTime().toString();
//✅ Case 5: Want to store spinner value in DB
//
//Right now we didn’t store type.
//
//👉 If needed:
//
//Change DB:
//
//type TEXT
//
//Then:
//
//cv.put("type", type);



//⚡ 2. DATE + TIME PICKER (DateTime)
//
//If they ask for datetime, you just chain DatePicker + TimePicker
//
//🧩 FULL CODE (Simple Exam Version)
//etDate.setOnClickListener(v -> {
//
//    Calendar c = Calendar.getInstance();
//
//    int year = c.get(Calendar.YEAR);
//    int month = c.get(Calendar.MONTH);
//    int day = c.get(Calendar.DAY_OF_MONTH);
//
//    DatePickerDialog dp = new DatePickerDialog(this,
//        (view, y, m, d) -> {
//
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            int minute = c.get(Calendar.MINUTE);
//
//            TimePickerDialog tp = new TimePickerDialog(this,
//                (view1, h, min) -> {
//
//                    String datetime = d + "/" + (m+1) + "/" + y +
//                                      " " + h + ":" + min;
//
//                    etDate.setText(datetime);
//
//                }, hour, minute, true);
//
//            tp.show();
//
//        }, year, month, day);
//
//    dp.show();
//});

//🧩 ✅ XML FOR DATE / DATETIME PICKER
//
//If you want it to look slightly better:
//
//android:inputType="none"
//
//Full version:
//
//<EditText
//    android:id="@+id/etDate"
//    android:hint="Select Date & Time"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:focusable="false"
//    android:clickable="true"
//    android:inputType="none"/>