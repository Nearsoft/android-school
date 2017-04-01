[\[Paso Anterior\]](04_new_activty.md)

# List Item

- CardView nos provee de un hermoso efecto sin tener que hacer mucho trabajo, para lograrlo, hay que modificar el archivo

`list_item.xml`

Para que su contenido sea el siguiente:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/cardViewContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    android:clickable="true"
    android:foreground="?android:attr/selectableItemBackground"
    android:minHeight="72dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerVertical="true"
        android:orientation="horizontal"
        android:padding="16dp">

        <CheckBox
            android:id="@+id/isDoneCheckbox"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:gravity="start" />

        <TextView
            android:id="@+id/toDoText"
            style="@style/TextAppearance.AppCompat.Body1"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="3"
            android:ellipsize="end"
            android:gravity="center"
            android:maxLines="1" />

        <TextView
            android:id="@+id/dateText"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="2"
            android:gravity="center"
            android:maxLines="1" />

        <ImageButton
            android:id="@+id/mapImageButton"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="#00ffffff"
            android:gravity="end"
            android:src="@drawable/ic_pin_accent" />

    </LinearLayout>
</android.support.v7.widget.CardView>
```

El archivo `@drawable/ic_pin_accent` no existe, pero es basicamente el archivo `@drawable/ic_pin_red`, solo creemos un nuevo archivo drawable xml y ponemos de nombre: `ic_pin_accent.xml` y su contenido es:

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportHeight="24.0"
    android:viewportWidth="24.0">
    <path
        android:fillColor="@color/colorAccent"
        android:pathData="M18,8c0,-3.31 -2.69,-6 -6,-6S6,4.69 6,8c0,4.5 6,11 6,11s6,-6.5 6,-11zM10,8c0,-1.1 0.9,-2 2,-2s2,0.9 2,2 -0.89,2 -2,2c-1.1,0 -2,-0.9 -2,-2zM5,20v2h14v-2L5,20z" />
</vector>
```

Corre la aplicación y deberias ver un nuevo efecto al hacer click en el card view!

[\[Siguiente Paso\]](06_edit_todo_transition.md)
