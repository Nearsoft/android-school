Notifications and Alarms
=====================

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

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id,notification);

    }
}
```
4. Necesitamos declarar nuestro reciever en el Manifest:
```XML         
    <receiver android:name=".activities.notification.NotificationPublisher"/>
```
5. Dentro del **activity_detail.xml** agregamos un nuevo switch:
```XML
<Switch
    android:id="@+id/switch_notification"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_below="@+id/switch_location"
    android:layout_alignParentStart="true"
    android:layout_marginTop="16dp"
    android:checked="false"
    android:text="@string/use_notification" />
 ```
y un nuevo string a **strings.xml** 
```XML
  <string name="use_notification">Notify me!</string>
```
6. Ahora dentro de nuestro modelo de **ToDoContent** hay que decirle que ahora tendra nuevos metodos para indicar que habrá notificaciones:

```java
  private boolean notify;
  		  
  public ToDoContent(String title, Date date, boolean hasDate, double lat, double lng, boolean notify) {
          this.title = title;
          this.date = date;
          this.hasDate = hasDate;
          this.lat = lat;
          this.lng = lng;
          this.notify = notify;
      }
  		  
  
   public boolean isNotify() {
       return notify;
   }
 
   public void setNotify(boolean notify) {
        this.notify = notify;
    }
 
     public boolean isHasDate() {
         return hasDate;
     }
```

7. Dentro de **DetailActivity** agregaremos lo siguiente:
    - Declaracion de nuestro nuevo switch:
        ```java 
        private Switch notificationSwitch
        ```
    - Bind de este switch a nuestra vista:
        ```java 
        notificationSwitch = (Switch) findViewById(R.id.switch_notification);
        ```
    - Dentro del metodo **enableToDoViewEdition** hay que habilitar el switch si se esta en modo de edición: 
        ```java
            notificationSwitch.setEnabled(isEditClicking);
        ```
    - Un método que prepare nuestra notificacion basada en una fecha:
        ```java
             public void prepareNotification(Notification notification, Date date){
                Intent notificationIntent = new Intent(this, NotificationPublisher.class);
                notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
                notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                long remainingMillis = SystemClock.elapsedRealtime() + date.getTime();
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, remainingMillis ,pendingIntent);
            }
        ```
    - Un metodo para obtener nuestra notificación:
        ```java
        public Notification getNotification(ToDoContent todoItem){
            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle(todoItem.getTitle())
                    .setContentText(todoItem.getDate().toString())
                    .setSmallIcon(R.drawable.ic_event_note_black);
            return builder.build();
        }
        ```
    - Agregarle su listener con el metodo nuevo :D:
    ```java
     notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    prepareNotification(getNotification(todoItem),todoItem.getDate());
                }
            }
        });
    ```

8. Ahora en el **MainActivity** hay que agregar los nuevos parametros a nuestra creacion de ToDo pero con un Date que sea 10 segundos despues de ser creada:
    ```java
            Date alarmTime = new Date(System.currentTimeMillis() + 1000);

            data.add(new ToDoContent("task 2", null, false, 29.09747, -111.02198, false));
            data.add(new ToDoContent("task 3", alarmTime, false, 29.09747, -111.02198, true));

 ```

