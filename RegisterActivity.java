package com.example.attendanceapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword;

    RadioGroup roleGroup;

    RadioButton student, instructor;

    Button btnRegister;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        roleGroup = findViewById(R.id.roleGroup);

        student = findViewById(R.id.student);
        instructor = findViewById(R.id.instructor);

        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DBHelper(this);

        btnRegister.setOnClickListener(v -> register());
    }

    private void register() {

        String name =
                edtName.getText().toString().trim();

        String email =
                edtEmail.getText().toString().trim();

        String password =
                edtPassword.getText().toString().trim();

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String role;

        if (student.isChecked()) {

            role = "Student";

        } else {

            role = "Instructor";
        }

        boolean result =
                dbHelper.registerUser(
                        name,
                        email,
                        password,
                        role
                );

        if (result) {

            Toast.makeText(
                    this,
                    "Registration successful",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Email already registered",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
