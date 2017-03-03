package com.nearsoft.androidschool.todoapp.activities.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra("notification");
        int id = intent.getIntExtra("notification-id", 0);
        notificationManager.notify(id,notification);

    }
}
