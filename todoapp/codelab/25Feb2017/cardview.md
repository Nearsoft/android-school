CardView
======
Primero hay que agregar la dependencia CardView a nuestro archivo build.gradle (Module: app):
```
dependencies {
	...
    compile 'com.android.support:cardview-v7:25.1.1'
	...
}
```
Esto nos permitirá agregar a nuestro archivo xml el tag ```<android.support.v7.widget.CardView/>```.

##Crear un archivo layout para nuestra CardView
Agregamos un nuevo archivo de layout:

<img src="https://lh3.googleusercontent.com/-ckVSZqWNKng/WLY1dFYVKiI/AAAAAAAAbQA/_USu_Clf0MwKWTvsFWxp2U3__jPNjMzaQCLcB/s0/Screen+Shot+2017-02-28+at+7.42.29+PM.png" width = 800>


En el campo Root element escribimos CardView:
![New Resource File](https://lh3.googleusercontent.com/-ou-zYhO-k-o/WLY2jgmK5HI/AAAAAAAAbQI/5LqshwQE5SEvVMlb2jrxuZPGNXYEbGSnACLcB/s0/Screen+Shot+2017-02-28+at+7.48.30+PM.png "new_resource")

##Código
El código que usaremos se verá como el siguiente:

```
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
En el código se contiene:
* Un CheckBox para marcar la tarea como hecha
* Un TextView para el título de nuestra tarea
* Otro TextView para la fecha
* Un ImageView que contiene un ícono de ubicación

El layout debería de verse así:
<div align="center">
<img src="https://lh3.googleusercontent.com/-X-3ZKJucHWA/WLY3rD9PoSI/AAAAAAAAbQQ/RE0xuJmH1Us43G3t4NoeerPx5_QF9A3gQCLcB/s0/Screen+Shot+2017-02-28+at+7.53.01+PM.png" width=400 align="">
</div>
