package com.example.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME =
            "AttendanceDB.db";

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {

        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "email TEXT UNIQUE," +
                        "password TEXT," +
                        "role TEXT)"
        );

        db.execSQL(
                "CREATE TABLE courses (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "course TEXT," +
                        "student TEXT)"
        );

        db.execSQL(
                "CREATE TABLE attendance (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "student TEXT," +
                        "course TEXT," +
                        "date TEXT," +
                        "status TEXT)"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS attendance");

        onCreate(db);
    }

    public boolean registerUser(
            String name,
            String email,
            String password,
            String role) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("role", role);

        long result = db.insert(
                "users",
                null,
                values
        );

        return result != -1;
    }

    public Cursor loginUser(
            String email,
            String password) {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password}
        );
    }

    public boolean addCourse(
            String course,
            String student) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("course", course);
        values.put("student", student);

        return db.insert(
                "courses",
                null,
                values
        ) != -1;
    }

    public boolean markAttendance(
            String student,
            String course,
            String date) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("student", student);
        values.put("course", course);
        values.put("date", date);
        values.put("status", "Present");

        return db.insert(
                "attendance",
                null,
                values
        ) != -1;
    }

    public Cursor getAttendance(
            String student) {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM attendance WHERE student=?",
                new String[]{student}
        );
    }
}
