[\[Paso Anterior\]](01_todo_content.md)

#activity_detail
Comenzaremos agregando un switch que nos permita utilizar nuestra location actual como la location del TODO.

En `activity_detail.xml` agregamos el siguiente switch:

```xml 
   <Switch
            android:id="@+id/switch_date"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_above="@+id/notes_label"
            android:layout_alignParentStart="true"
            android:checked="true"
            android:text="@string/set_a_date" />
``` 

También necesitamos aumentar la propiedad `layout_marginTop` de el `TextView` con id  `notes_label`. Lo aumentamos de 16dp a **64dp**  para dar espacio al nuevo switch.
```xml
     <TextView
            android:id="@+id/notes_label"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/date_view"
            android:layout_marginTop="64dp"
            android:labelFor="@+id/notes_field"
            android:text="@string/notes_label" />
``` 

`activity_detail.xml` se debe ver asi con el nuevo switch:

<img src="http://i.imgur.com/FEQH5tf.png" height = 500px>

[\[Siguiente Paso\]](03_activity_detail.md)

