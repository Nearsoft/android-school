# Main Activity View

- En este punto, tenemos un pequeño detalle en el archivo: `activity_todomain.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_todo_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.nearsoft.androidschool.todoapp.activities.main.MainActivity">


    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp"
        android:scrollbars="vertical" />


    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add_white"
        app:layout_anchor="@id/recycler_view"
        app:layout_anchorGravity="bottom|right|end" />

</android.support.design.widget.CoordinatorLayout>
```
El detalle es que teniamos un `RelativeLayout` extra, que no era necesario.

## READ Operation

Hemos terminado, la operación de READ, podemos mostrar los datos, la vista la iremos mejorando.

En este punto, podemos continuar.
