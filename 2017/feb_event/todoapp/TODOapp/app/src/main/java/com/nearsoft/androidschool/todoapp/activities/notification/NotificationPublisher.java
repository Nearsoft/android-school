package com.nearsoft.androidschool.todoapp.activities.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bundle todoBundle =  intent.getExtras().getBundle(AlarmHandler.TODO_KEY);
        ToDoContent todoItem = (ToDoContent) todoBundle.getSerializable(AlarmHandler.TODO_KEY);
        Intent todoIntent = new Intent(context, DetailActivity.class);

        todoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        todoIntent.putExtra(DetailActivity.EXTRA_TODO_KEY, todoItem);

        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0, todoIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(todoItem.getTitle())
                .setContentText(todoItem.getNotes())
                .setContentIntent(notificationIntent)
                .setSmallIcon(R.drawable.ic_event_note_black);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(todoItem.getId().intValue(), notification);
    }
}
