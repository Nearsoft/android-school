Notifications and Alarms
=====================

Clona el siguiente repositorio para estar sincronizados todos.

```shell
git clone https://github.com/Nearsoft/android-school.git
```

y ejecuta el siguiente comando:

```shell
git reset --hard 92e4823094e24e6d51a1064d0a40262fa21ea3c0
```

En este codelab crearemos una actividad que nos permita generar una alarma en nuestro TODO, la cual se pondra automáticamente 10 segundos después de que se guardó el TODO.

1. Crearemos un nuevo paquete dentro de activities con el nombre notification:
<img src="http://image.prntscr.com/image/3221873a79c94f8badfa9c2c17036f4c.png">

2. Dentro de ella crear una clase que se llame **NotificationPublisher**
<img src="http://image.prntscr.com/image/0c19ff3ff4054c58a17d2172b4fec94e.png">

3. El contenido de esta será el siguiente: 
```java
package com.nearsoft.androidschool.todoapp.activities.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver{
    public static String NOTIFICATION = "notification";
    public static String NOTIFICATION_ID = "notification-id";


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
```
[\[Siguiente Paso\]](02_TODOContent.md)