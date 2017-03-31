[img_1]:http://i.imgur.com/AT1bxwj.png
[img_2]:http://i.imgur.com/ZyokwNf.png
# Enter Detail Activity

- **Crearemos una nueva actividad** llamada `DetailActivity.java`, debera estar en el paquete

`activities.detail.DetailActivity`

`AndroidManifest.xml` deberia verse asi, incluyendo la nueva actividad `DetailActivity`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.nearsoft.androidschool.todoapp">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".activities.main.MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".activities.detail.DetailActivity"
             android:parentActivityName=".activities.main.MainActivity"
            />
    </application>

</manifest>
```

- Ahora, creamos la clase `DetailActivity.java` dentro del paquete `activities.detail`.

```java
package com.nearsoft.androidschool.todoapp.activities.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "TODO";

    private TextView titleTextView;
    private TextView notesTextView;

    private ToDoContent todo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todo = getTodo();
        setContentView(R.layout.activity_detail);
        titleTextView = (TextView) findViewById(R.id.title);
        notesTextView = (TextView) findViewById(R.id.notes);
        titleTextView.setText(todo.getTitle());
        notesTextView.setText(todo.getNotes());
    }

    private ToDoContent getTodo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        }
        throw new AssertionError(getClass().getSimpleName() + " intent extras should contain an item");
    }

}
```

Podemos ver que carga el content view con este layout:

`setContentView(R.layout.activity_detail);`

Así que necesitaremos un archivo `activity_detail.xml` para definir nuestras vistas:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_todo_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/activity_vertical_margin"
    tools:context="com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:lines="1"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Title"
            tools:text="todo title will be here and if it is long AF it will be hidden" />

        <TextView
            android:id="@+id/notes"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/title"
            android:layout_marginTop="16dp"
            android:maxHeight="300dp"
            tools:text="notes of the todo, this is a sample text.\ncan contain end of lines\nsomething here" />

    </RelativeLayout>

</android.support.design.widget.CoordinatorLayout>
```

Corremos la aplicación y deberiamos ver algo asi:

![img_1]
![img_2]
