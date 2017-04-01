[img_date]:http://imgur.com/kksjG9r.png

[\[Paso Anterior\]](09_add_date.md)

# Date & Todos

Vamos a agregar un nuevo atributo a nuestros Todos, ahora queremos una fecha en la que deberia notificarnos nuestra aplicación, aun no haremos las notificaciones pero si prepararemos la aplicación para el futuro.

Si estas perdido en este punto, puedes clonar el repositorio e ir directo a este commit: `2b282a6beba420484687f609f03a5f787fbae42a`

```bash
git clone https://github.com/Nearsoft/android-school.git
git reset --hard 2b282a6beba420484687f609f03a5f787fbae42a
```

Tendras todo lo necesario para seguir con el proyecto.

## Date on Model
- Lo primero que tenemos que hacer es agregar datos a nuestro modelo:


`ToDoContent.java`
```java
package com.nearsoft.androidschool.todoapp.models;

import java.io.Serializable;
import java.util.Date;

public class ToDoContent implements Serializable {
    private String title;
    private Date date;
    private boolean done;
    private String location;
    private boolean starred;
    private String notes;

    public ToDoContent(String title, Date date, String location) {
        this.title = title;
        this.date = date;
        this.location = location;
    }

    public ToDoContent() {
        this.date = new Date();
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean hasDate() {
        return this.date != null;
    }
}


```

Ahora, en lugar de pasar un `String` en el constructor, mandamos un `Date`:

`MainActivity.java`

```java
    public List<ToDoContent> getData() {
        List<ToDoContent> data = new ArrayList<>();
        ToDoContent first = new ToDoContent("task 1", new Date(), "at Nearsoft");
        first.setNotes("sample text, text sample, hehe hehe\nmore text, here is another text and more samples\nsampletext, stub, lalala i hate the word \"fake\"");
        data.add(first);
        data.add(new ToDoContent("task 2", new Date(), "at Nearsoft"));
        data.add(new ToDoContent("task 3", new Date(), "at Caffenio"));
        return data;
    }
```

## Fragments

Recuerdan los fragmentos?, volvieron, en forma de fichas!

Vamos a usar un fragmento para desplegar un DatePicker, un componente muy utilizado en Android para elegir fechas:

Crea un paquete llamado `fragment`

Y dentro de el la clase:

`DatePickerFragment.java`

```java
package com.nearsoft.androidschool.todoapp.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (listener == null) {
            throw new IllegalAccessError("listener is null, call DatePickerFragment#attachListener first");
        }

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

    public void attachListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDestroy() {
        listener = null;
        super.onDestroy();
    }
}
```

- Debemos agregar algunos nuevas propiedades en la actividad de detalle para agregar y ver fechas:

`activity_detail.xml`

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
            android:layout_alignParentTop="true"
            android:labelFor="@+id/title_field"
            android:text="@string/task_name" />

        <EditText
            android:id="@+id/title_field"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/title_label"
            android:ellipsize="end"
            android:lines="1"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Title"
            tools:text="todo title will be here and if it is long AF it will be hidden" />

        <include
            android:id="@+id/date_view"
            layout="@layout/date_layout"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentEnd="true"
            android:layout_below="@+id/title_field"
            android:layout_marginTop="32dp" />

        <Switch
            android:id="@+id/switch_date"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_above="@+id/notes_label"
            android:layout_alignParentStart="true"
            android:checked="true"
            android:text="@string/set_a_date" />

        <TextView
            android:id="@+id/notes_label"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/date_view"
            android:layout_marginTop="16dp"
            android:labelFor="@+id/notes_field"
            android:text="@string/notes_label" />

        <EditText
            android:id="@+id/notes_field"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/notes_label"
            android:layout_marginTop="16dp"
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
        android:visibility="invisible"
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
        app:layout_anchor="@id/content"
        app:layout_anchorGravity="bottom|right|end" />

</android.support.design.widget.CoordinatorLayout>
```

**Ojo:** Estamos usando un nuevo keyword:

```xml
<include
    android:id="@+id/date_view"
    layout="@layout/date_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentStart="true" />
```

`include` lo que hace es tal cual, traducido: `incluir` un layout file dentro de otro, asi puedes compartir layouts, en este caso solo es ilustrativo, no es necesario hacerlo siempre ni tiene un problema de performance.

Esto tambien nos permite tener mas pequeños nuestros layouts.

Necesitamos crear el `date_layout.xml`

Que incluira lo siguiente:

`date_layout.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:id="@+id/date"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:clickable="true"
    android:foreground="?android:attr/selectableItemBackground"
    card_view:cardCornerRadius="0dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:src="@drawable/ic_event"
            android:tint="@color/colorPrimaryDark" />

        <TextView
            android:id="@+id/date_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:paddingEnd="5dp"
            android:paddingStart="5dp"
            android:text="@string/date"
            android:textAppearance="@style/TextAppearance.AppCompat.Body1" />

    </LinearLayout>

</android.support.v7.widget.CardView>
```

Tambien agregamos un nuevo drawable: `ic_event`

`ic_event.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24.0"
    android:viewportHeight="24.0">
    <path
        android:fillColor="#FF000000"
        android:pathData="M17,12h-5v5h5v-5zM16,1v2L8,3L8,1L6,1v2L5,3c-1.11,0 -1.99,0.9 -1.99,2L3,19c0,1.1 0.89,2 2,2h14c1.1,0 2,-0.9 2,-2L21,5c0,-1.1 -0.9,-2 -2,-2h-1L18,1h-2zM19,19L5,19L5,8h14v11z"/>
</vector>
```

Ahora los cambios importantes en `DetailActivity` para poder hacer funcionar el Fragmento y todo:

```java
package com.nearsoft.androidschool.todoapp.activities.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.fragment.DatePickerFragment;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.text.DateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "TODO";

    private EditText titleEditTextView;
    private EditText notesEditTextView;
    private CardView dateCardView;
    private TextView dateTextView;
    private FloatingActionButton editFab;
    private FloatingActionButton saveFab;
    private Switch dateSwitch;

    private ToDoContent todoItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        setSwitchListener();
        populateToDoObject();
        setSaveListener();
        setEditListener();
        setDateListener();
    }

    private void setViews() {
        setContentView(R.layout.activity_detail);
        titleEditTextView = (EditText) findViewById(R.id.title_field);
        notesEditTextView = (EditText) findViewById(R.id.notes_field);
        dateCardView = (CardView) findViewById(R.id.date_view);
        editFab = (FloatingActionButton) findViewById(R.id.edit_fab);
        saveFab = (FloatingActionButton) findViewById(R.id.save_fab);
        dateTextView = (TextView) dateCardView.findViewById(R.id.date_text);
        dateSwitch = (Switch) findViewById(R.id.switch_date);
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

    private void setDateListener() {
        dateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.attachListener(new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Date dateTime = new Date(year, month + 1, dayOfMonth, 0, 0);
                updateDate(dateTime);
            }
        });
        dialog.show(this.getSupportFragmentManager(), "datePicker");
    }

    private void setSwitchListener() {
        dateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dateCardView.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    private void buttonViewConfig(boolean isEditClicking) {
        titleEditTextView.setEnabled(isEditClicking);
        notesEditTextView.setEnabled(isEditClicking);
        dateCardView.setEnabled(isEditClicking);
        dateSwitch.setEnabled(isEditClicking);
        editFab.setVisibility(isEditClicking ? View.INVISIBLE : View.VISIBLE);
        saveFab.setVisibility(isEditClicking ? View.VISIBLE : View.INVISIBLE);
    }

    private void saveData() {
        String selectedDate = dateTextView.getText().toString();
        if (selectedDate.equals(getText(R.string.date))) {
            throw new AssertionError(dateCardView.getClass().getSimpleName() + getString(R.string.set_date_or_change_switch_message));
        }
        todoItem.setTitle(titleEditTextView.getText().toString());
        todoItem.setDate(new Date(selectedDate));
        todoItem.setNotes(notesEditTextView.getText().toString());
    }

    private void populateToDoObject() {
        todoItem = getTodo();
        if (todoItem.getTitle() != null) {
            displayDetail();
            buttonViewConfig(false);
        }
    }

    private void displayDetail() {
        titleEditTextView.setText(todoItem.getTitle());
        notesEditTextView.setText(todoItem.getNotes());
        if (todoItem.getDate() != null) {
            updateDate(todoItem.getDate());
        }
        dateSwitch.setChecked(todoItem.hasDate());
    }

    private ToDoContent getTodo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        } else {
            return new ToDoContent();
        }
    }

    public void updateDate(Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String dateText = dateFormat.format(date);
        dateTextView.setText(dateText);
    }

}
```

Tenemos que agregar los nuevos strings a `strings.xml`:

```xml
<string name="date">Date</string>
<string name="set_a_date">Set a Date</string>
<string name="set_date_or_change_switch_message">You have to select a valid date or turn off the switch</string>
```

Si corren la app, probablemente tengas un error en el `TodoAdapter`, se arregla con un simple cambio en la linea erronea:

```java
if (toDoItem.hasDate()) {
    dateTextBox.setText(toDoItem.getDate().toString());
}
```

Con esto, deberia ser posible ver:

![img_date]

Con esto termina el code lab.
