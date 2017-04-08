package com.nearsoft.androidschool.todoapp.activities.notification;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nearsoft.androidschool.todoapp.database.ToDoDbHelper;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.List;

public class AlarmHandler {

    private final Context context;
    private ToDoDbHelper toDoDbHelper;
    public static final String TODO_KEY = "TODO";

    private List<ToDoContent> notificationTodos;

    public AlarmHandler(Context context) {
        this.context = context;
        toDoDbHelper = new ToDoDbHelper(context);
    }

    public void deleteAlarm(ToDoContent todoItem) {
        setAlarm(todoItem, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void onBoot() {
        notificationTodos = toDoDbHelper.getAllNotificationTodos();
        for (ToDoContent tp:
             notificationTodos) {
            createOrUpdateAlarm(tp);
        }
    }

    public void createOrUpdateAlarm(ToDoContent todoItem) {
        if (todoItem.isNotify()) {
            setAlarm(todoItem, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            deleteAlarm(todoItem);
        }
    }

    private PendingIntent getPendingIntent(ToDoContent todoItem, int pendingIntentFlag) {
        Intent todoIntent = new Intent(context, NotificationPublisher.class);
        Bundle todoBundle = new Bundle();
        todoBundle.putSerializable(TODO_KEY, todoItem);
        todoIntent.putExtra(TODO_KEY, todoBundle);
        return PendingIntent.getBroadcast(context, todoItem.getId().intValue(), todoIntent, pendingIntentFlag);
    }

    private void setAlarm(ToDoContent todoItem, int pendingIntentFlag) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, todoItem.getDate().getTime(), getPendingIntent(todoItem, pendingIntentFlag));
    }

}
