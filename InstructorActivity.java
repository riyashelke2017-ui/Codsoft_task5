package com.example.attendanceapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorActivity extends AppCompatActivity {

    EditText edtCourse, edtStudent;

    Button btnAdd;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instructor);

        edtCourse = findViewById(R.id.edtCourse);

        edtStudent = findViewById(R.id.edtStudent);

        btnAdd = findViewById(R.id.btnAdd);

        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(v -> addCourse());
    }

    private void addCourse() {

        String course =
                edtCourse.getText().toString().trim();

        String student =
                edtStudent.getText().toString().trim();

        if (course.isEmpty() || student.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter course and student name",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        boolean result =
                dbHelper.addCourse(
                        course,
                        student
                );

        if (result) {

            Toast.makeText(
                    this,
                    "Course and student added",
                    Toast.LENGTH_SHORT
            ).show();

            edtCourse.setText("");
            edtStudent.setText("");

        } else {

            Toast.makeText(
                    this,
                    "Unable to add record",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
