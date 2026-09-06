package com.example.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView txtWelcome;

    Button btnStudent;
    Button btnInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.txtWelcome);

        btnStudent = findViewById(R.id.btnStudent);

        btnInstructor = findViewById(R.id.btnInstructor);

        String name =
                getIntent().getStringExtra("name");

        String role =
                getIntent().getStringExtra("role");

        txtWelcome.setText(
                "Welcome, " + name
                        + "\nRole: " + role
        );

        btnStudent.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            this,
                            StudentActivity.class
                    );

            intent.putExtra("studentName", name);

            startActivity(intent);
        });

        btnInstructor.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            this,
                            InstructorActivity.class
                    );

            startActivity(intent);
        });
    }
}
