[img_create_btn]:http://i.imgur.com/jdxYkB0.png
[img_edit_btn]:http://imgur.com/ZorimYr.png

# CREATE & UPDATE Operation

- Ahora crearemos la operación de CREATE (valgame).

En este punto, tenemos los botones de editar cada TODO y CREAR nuevos todos, solo tenemos que agregarles logica.

![img_create_btn]

![img_edit_btn]

Comencemos modificando la actividad de detalle `DetailActivity.java` para que pueda soportar 2 tipos de operaciones:

1. Mostrar
2. Editar (Crear & Actualizar)

```java
package com.nearsoft.androidschool.todoapp.activities.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "TODO";

    private EditText titleEditTextView;
    private EditText notesEditTextView;
    private EditText dateEditTextView;
    private FloatingActionButton editFab;
    private FloatingActionButton saveFab;

    private ToDoContent todo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        setSaveListener();
        setEditListener();
        getToDoObject();
    }

    private void getToDoObject() {
        todo = getTodo();
        if(todo.getTitle().isEmpty()) {
            buttonViewConfig(true);
        } else {
            displayDetail();
        }
    }

    private void setViews() {
        setContentView(R.layout.activity_detail);
        titleEditTextView = (EditText) findViewById(R.id.title_field);
        notesEditTextView = (EditText) findViewById(R.id.notes_field);
        dateEditTextView = (EditText) findViewById(R.id.date_text);
        editFab = (FloatingActionButton) findViewById(R.id.edit_fab);
        saveFab = (FloatingActionButton) findViewById(R.id.save_fab);
    }

    private void setSaveListener() {
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Snackbar.make(editFab, "Data Saved (Not saving in reality)", Snackbar.LENGTH_SHORT).show();
                buttonViewConfig(false);
            }
        });
    }

    private void setEditListener() {
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonViewConfig(true);
            }
        });
    }

    private void buttonViewConfig(boolean isEditClicking) {
        titleEditTextView.setEnabled(isEditClicking);
        notesEditTextView.setEnabled(isEditClicking);
        dateEditTextView.setEnabled(isEditClicking);
        editFab.setVisibility(isEditClicking ? View.INVISIBLE : View.VISIBLE);
        saveFab.setVisibility(isEditClicking ? View.VISIBLE : View.INVISIBLE);
    }

    private void saveData() {
        todo.setTitle(titleEditTextView.getText().toString());
        todo.setDate(dateEditTextView.getText().toString());
        todo.setNotes(notesEditTextView.getText().toString());
    }

    private void displayDetail() {
        titleEditTextView.setText(todo.getTitle());
        notesEditTextView.setText(todo.getNotes());
        dateEditTextView.setText(todo.getDate());
    }

    private ToDoContent getTodo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        } else {
            return new ToDoContent();
        }
    }
}
```

Tambien tendremos que actualizar el layout, el layout de la actividad de detalle es:

`activity_detail.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_edit"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity">

    <RelativeLayout
        android:id="@+id/content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="@dimen/activity_vertical_margin">

        <TextView
            android:id="@+id/title_label"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:labelFor="@+id/title_field"
            android:text="@string/task_name" />

        <EditText
            android:id="@+id/title_field"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/title_label"
            android:ellipsize="end"
            android:enabled="false"
            android:lines="1"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Title"
            tools:text="todo title will be here and if it is long AF it will be hidden" />

        <EditText
            android:id="@+id/date_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/title_field"
            android:layout_marginTop="10dp"
            android:enabled="false"
            android:gravity="end"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Small"
            tools:text="01/01/1990" />

        <TextView
            android:id="@+id/notes_label"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_above="@+id/notes_field"
            android:labelFor="@+id/notes_field"
            android:text="@string/notes_label" />

        <EditText
            android:id="@+id/notes_field"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/date_text"
            android:layout_marginTop="16dp"
            android:enabled="false"
            android:maxHeight="300dp"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Body1"
            tools:text="notes of the todo, this is a sample text.\ncan contain end of lines\nsomething here" />


    </RelativeLayout>

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/edit_fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_margin="16dp"
        android:src="@drawable/ic_edit_white"
        app:layout_anchor="@id/content"
        app:layout_anchorGravity="bottom|right|end" />

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/save_fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_margin="16dp"
        android:src="@drawable/ic_done_white"
        android:visibility="invisible"
        app:layout_anchor="@id/content"
        app:layout_anchorGravity="bottom|right|end" />

</android.support.design.widget.CoordinatorLayout>
```

Tambien agregamos 2 nuevos string values:

`strings.xml`

```xml
<string name="task_name">Task Name:</string>
<string name="notes_label">notes:</string>
```

Si hace falta agregar `ic_done_white`, este es el drawable que puedes utilizar:

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportHeight="24.0"
    android:viewportWidth="24.0">
    <path
        android:fillColor="@android:color/white"
        android:pathData="M9,16.2L4.8,12l-1.4,1.4L9,19 21,7l-1.4,-1.4L9,16.2z" />
</vector>
```

## Main Activity -> CREATE OPERATION

- Necesitamos hacer que el boton de '+', nos lleve a la creación de un nuevo TODO Item.

En el `MainActivity.java` hay que buscar la sección donde se encuentra esto:

```java
addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(todoRecyclerView, "this should take you to and activity to add a new task ", Snackbar.LENGTH_SHORT).show();
            }
        })
```

Y aqui es donde tenemos que crear un intent y mandar llamar a la actividad de Creación.

Para hacer esto, podemos hacer lo siguiente:

```java
Intent intent = new Intent(view.getContext(), DetailActivity.class);
view.getContext().startActivity(intent);
```

Al finalizar esto, deberiamos poder agregar un TodoItem, editar un TodoItem existente y ver un todo item existente.

Claro, sin guardar los datos - aun.

En este punto, podemos continuar.
