package com.example.database;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "registration1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_EMAIL = "email";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_NUMBER + " TEXT, " +
                COLUMN_EMAIL + " TEXT);";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
    // Insert data into the database
    public void insertData(String name,String number, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, number);
        values.put(COLUMN_EMAIL, email);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    // Retrieve data from the database
    public String getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id =
                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                @SuppressLint("Range") String name =
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String number =
                        cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
                @SuppressLint("Range") String email =
                        cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                stringBuilder.append("ID: ").append(id).append(", Name: ").append(name).append(", Number: ").append(number).append(", Email: ").append(email).append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return stringBuilder.toString();
    }
}

