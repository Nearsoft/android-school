[\[Paso Anterior\]](02_TODOContent.md)


6. Dentro del **activity_detail.xml** agregamos un nuevo switch:
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
<img src="http://image.prntscr.com/image/5f9e25f6a3834125b4e6d1262543b12d.png"/>

[\[Siguiente Paso\]](04_DetailActivity.md)
