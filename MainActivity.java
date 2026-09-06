package com.example.attendanceapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;

    Button btnLogin;

    TextView btnRegister;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);

        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(v -> login());

        btnRegister.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            MainActivity.this,
                            RegisterActivity.class
                    )
            );
        });
    }

    private void login() {

        String email =
                edtEmail.getText().toString().trim();

        String password =
                edtPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Cursor cursor =
                dbHelper.loginUser(
                        email,
                        password
                );

        if (cursor.moveToFirst()) {

            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("name")
                    );

            String role =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("role")
                    );

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            DashboardActivity.class
                    );

            intent.putExtra("name", name);
            intent.putExtra("role", role);

            startActivity(intent);

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Invalid email or password",
                    Toast.LENGTH_SHORT
            ).show();
        }

        cursor.close();
    }
}
