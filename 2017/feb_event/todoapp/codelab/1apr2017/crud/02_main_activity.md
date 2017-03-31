# Main Activity Rename

- Actualmente tenemos la clase `ToDoMain.java`, pero ese es un nombre equivocado, cambiemos el nombre de la clase a `MainActivity.java`

- Cambiemos el contenido de `MainActivity.java` por el siguiente:

```java
package com.nearsoft.androidschool.todoapp.activities.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.main.adapter.ToDoListAdapter;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoListAdapter adapter;
    private FloatingActionButton addFab;
    private RecyclerView todoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);

        addFab = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new ToDoListAdapter(getData());

        todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setAdapter(adapter);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(todoRecyclerView, "this should take you to and activity to add a new task ", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public List<ToDoContent> getData() {
        List<ToDoContent> data = new ArrayList<>();
        data.add(new ToDoContent("task 1", "Today", "at Nearsoft"));
        data.add(new ToDoContent("task 2", "Today", "at Nearsoft"));
        data.add(new ToDoContent("task 3", "yesterday", "at Cafenio"));
        return data;
    }
}
```

Tambien tenemos que cambiar el layout del activity `activity_todomain.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_todo_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.nearsoft.androidschool.todoapp.activities.main.MainActivity">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recycler_view"
            android:scrollbars="vertical"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:padding="16dp" />

    </RelativeLayout>

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:layout_marginBottom="10dp"
        android:layout_marginEnd="10dp"
        android:src="@drawable/ic_add_white"
        app:layout_anchor="@id/recycler_view"
        app:layout_anchorGravity="bottom|right|end" />

</android.support.design.widget.CoordinatorLayout>
```

Si tratamos de correr la app, probablemente truene.

- Actualizemos el `AndroidManifest.xml` para que se vea reflejado el cambio de nombre de la actividad:

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

Teniendo esto, podemos continuar.
