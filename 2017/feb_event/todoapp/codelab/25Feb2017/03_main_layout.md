[image]: http://i.imgur.com/SFPihLo.png

[\[Paso Anterior\]](02_main_activity.md)

# Main Layout

Lo que vimos anteriormente en la pantalla, se puede ver de la siguiente manera en codigo:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.nearsoft.android.todoapp.MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>
```

Este codigo XML es el que tiene toda la definicion de las vistas que estaremos utilizando, en el dia 3 de Android School veremos mas a detalle todo esto, de momento, podemos jugar con los diferentes valores que tiene este TextView.

Esto lo podemos encontrar en el archivo: [/todoapp/TODOapp/app/src/main/res/layout/activity_main.xml](/todoapp/TODOapp/app/src/main/res/layout/activity_main.xml)

[\[Siguiente Paso\]](04_github.md)
