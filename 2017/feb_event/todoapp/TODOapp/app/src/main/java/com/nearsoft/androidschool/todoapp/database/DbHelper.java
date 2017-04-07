package com.nearsoft.androidschool.todoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nearsoft.androidschool.todoapp.database.ToDoDbContract.ToDoTable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoApp.db";

    private static final String SQL_CREATE =
            "CREATE TABLE " + ToDoTable.TABLE_NAME + " (" +
                    ToDoTable._ID + " INTEGER PRIMARY KEY," +
                    ToDoTable.COLUMN_NAME_TITLE + " TEXT," +
                    ToDoTable.COLUMN_NAME_DATE + " DATETIME," +
                    ToDoTable.COLUMN_NAME_HAS_DATE + " BOOLEAN NOT NULL CHECK (" + ToDoTable.COLUMN_NAME_DONE + " IN (0,1))," +
                    ToDoTable.COLUMN_NAME_DONE + " BOOLEAN NOT NULL CHECK (" + ToDoTable.COLUMN_NAME_DONE + " IN (0,1))," +
                    ToDoTable.COLUMN_NAME_LATITUDE + " DOUBLE," +
                    ToDoTable.COLUMN_NAME_LONGITUDE + " DOUBLE," +
                    ToDoTable.COLUMN_NAME_STARRED + " BOOLEAN NOT NULL CHECK (" + ToDoTable.COLUMN_NAME_STARRED + " IN (0,1))," +
                    ToDoTable.COLUMN_NAME_NOTES + " TEXT," +
                    ToDoTable.COLUMN_NAME_NOTIFY + " BOOLEAN NOT NULL CHECK (" + ToDoTable.COLUMN_NAME_NOTIFY + " IN (0,1)))";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
