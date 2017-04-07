package com.nearsoft.androidschool.todoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nearsoft.androidschool.todoapp.database.ToDoDbContract.ToDoTable;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ToDoDbHelper {

    private final DbHelper dbHelper;
    private final SimpleDateFormat dateFormat;

    public ToDoDbHelper(Context context) {
        dbHelper = new DbHelper(context);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    public long saveToDo(ToDoContent toDo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.insert(ToDoTable.TABLE_NAME, null, getValuesFromToDo(toDo));
    }

    public void updateToDo(ToDoContent toDo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = ToDoTable._ID + " = ?";
        String[] selectionArgs = {toDo.getId().toString()};
        db.update(ToDoTable.TABLE_NAME, getValuesFromToDo(toDo), selection, selectionArgs);
    }

    public void deleteToDo(ToDoContent toDo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = ToDoTable._ID + " = ?";
        String[] selectionArgs = {toDo.getId().toString()};
        db.delete(ToDoTable.TABLE_NAME, selection, selectionArgs);
    }

    public ToDoContent getToDoById(Long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = ToDoTable._ID + " = ?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = db.query(ToDoTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        ToDoContent toDo = null;
        while (cursor.moveToNext()) {
            toDo = getToDoFromCursor(cursor);
        }
        return toDo;
    }

    public List<ToDoContent> getAllToDos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ToDoTable.TABLE_NAME, null, null, null, null, null, null);
        List<ToDoContent> toDos = new ArrayList<>();
        while (cursor.moveToNext()) {
            toDos.add(getToDoFromCursor(cursor));
        }
        cursor.close();
        return toDos;
    }

    private ToDoContent getToDoFromCursor(Cursor cursor) {
        ToDoContent toDo = new ToDoContent();
        toDo.setTitle(cursor.getString(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_TITLE)));
        toDo.setHasDate(cursor.getInt(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_HAS_DATE)) == 1);
        toDo.setDone(cursor.getInt(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_DONE)) == 1);
        toDo.setLat(cursor.getDouble(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_LATITUDE)));
        toDo.setLng(cursor.getDouble(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_LONGITUDE)));
        toDo.setStarred(cursor.getInt(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_STARRED)) == 1);
        toDo.setNotes(cursor.getString(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_NOTES)));
        toDo.setNotify(cursor.getInt(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_NOTIFY)) == 1);
        toDo.setId(cursor.getLong(cursor.getColumnIndex(ToDoTable._ID)));
        try {
            toDo.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(ToDoTable.COLUMN_NAME_DATE))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return toDo;
    }

    private ContentValues getValuesFromToDo(ToDoContent toDo) {
        ContentValues values = new ContentValues();
        values.put(ToDoTable.COLUMN_NAME_TITLE, toDo.getTitle());
        values.put(ToDoTable.COLUMN_NAME_DATE, dateFormat.format(toDo.getDate()));
        values.put(ToDoTable.COLUMN_NAME_HAS_DATE, toDo.hasDate());
        values.put(ToDoTable.COLUMN_NAME_DONE, toDo.isDone());
        values.put(ToDoTable.COLUMN_NAME_LATITUDE, toDo.getLat());
        values.put(ToDoTable.COLUMN_NAME_LONGITUDE, toDo.getLng());
        values.put(ToDoTable.COLUMN_NAME_STARRED, toDo.isStarred());
        values.put(ToDoTable.COLUMN_NAME_NOTES, toDo.getNotes());
        values.put(ToDoTable.COLUMN_NAME_NOTIFY, toDo.isNotify());
        return values;
    }

    public void onDestroy() {
        dbHelper.close();
    }
}
