package com.example.attendanceapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity {

    EditText edtCourse;

    Button btnMark;

    TextView txtAttendance;

    DBHelper dbHelper;

    String studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student);

        edtCourse = findViewById(R.id.edtCourse);

        btnMark = findViewById(R.id.btnMark);

        txtAttendance =
                findViewById(R.id.txtAttendance);

        dbHelper = new DBHelper(this);

        studentName =
                getIntent().getStringExtra(
                        "studentName"
                );

        btnMark.setOnClickListener(v ->
                markAttendance());

        showAttendance();
    }

    private void markAttendance() {

        String course =
                edtCourse.getText().toString().trim();

        if (course.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter course name",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String date =
                new java.text.SimpleDateFormat(
                        "dd/MM/yyyy",
                        java.util.Locale.getDefault()
                ).format(new java.util.Date());

        boolean result =
                dbHelper.markAttendance(
                        studentName,
                        course,
                        date
                );

        if (result) {

            Toast.makeText(
                    this,
                    "Attendance marked",
                    Toast.LENGTH_SHORT
            ).show();

            edtCourse.setText("");

            showAttendance();
        }
    }

    private void showAttendance() {

        Cursor cursor =
                dbHelper.getAttendance(studentName);

        StringBuilder data =
                new StringBuilder();

        while (cursor.moveToNext()) {

            String course =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "course"
                            )
                    );

            String date =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "date"
                            )
                    );

            data.append(course)
                    .append(" - ")
                    .append(date)
                    .append(" - Present\n");
        }

        cursor.close();

        if (data.length() == 0) {

            txtAttendance.setText(
                    "No attendance records"
            );

        } else {

            txtAttendance.setText(data.toString());
        }
    }
}
