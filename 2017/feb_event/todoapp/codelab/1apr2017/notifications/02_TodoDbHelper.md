[\[Paso Anterior\]](01_createNotificationPublisher.md)

4. Necesitamos declarar nuestro reciever en el Manifest:
```XML         
    <receiver android:name=".activities.notification.NotificationPublisher">
            <intent-filter>
                <action android:name="com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity"/>
            </intent-filter>
        </receiver>
```

5. in **TodoDbHelper**
```java

    public List<ToDoContent> getAllNotificationTodos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = ToDoTable.COLUMN_NAME_NOTIFY + " = ? AND " + ToDoTable.COLUMN_NAME_DATE + "> date('now')";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.query(ToDoTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        List<ToDoContent> toDos = new ArrayList<>();
        while (cursor.moveToNext()) {
            toDos.add(getToDoFromCursor(cursor));
        }
        cursor.close();
        return toDos;
    }
```
[\[Siguiente Paso\]](03_layout.md)

