[\[Paso Anterior\]](06_main_activity_refactor.md)

# Detail Activity Refactor

Ok se supone que ya podemos ver la lista de ToDos que tenemos en nuestra base de datos dentro de `MainActivity` pero no tenemos ningun todo por que aun no implementamos los metodos de guardado en nuestra ui, para resolver esto vamos a cambiar el contenido de `DetailActivity` por el siguiente:

```java
package com.nearsoft.androidschool.todoapp.activities.detail;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.nearsoft.androidschool.todoapp.activities.notification.NotificationPublisher;
import com.nearsoft.androidschool.todoapp.database.ToDoDbHelper;
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
    private Switch locationSwitch;
    private Switch notificationSwitch;
    private ToDoContent todoItem;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION_ENABLED = 0;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private ToDoDbHelper toDoDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        setSwitchListener();
        populateToDoObject();
        setSaveListener();
        setEditListener();
        setDateListener();
        toDoDbHelper = new ToDoDbHelper(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        toDoDbHelper.onDestroy();
        super.onDestroy();
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
        locationSwitch = (Switch) findViewById(R.id.switch_location);
        notificationSwitch = (Switch) findViewById(R.id.switch_notification);
    }

    private void setSaveListener() {
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Snackbar.make(editFab, "Data Saved", Snackbar.LENGTH_SHORT).show();
                enableToDoViewEdition(false);
            }
        });
    }

    private void setEditListener() {
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableToDoViewEdition(true);
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
                Date dateTime = new Date(year, month, dayOfMonth, 0, 0);
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
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prepareNotification(getNotification(todoItem), todoItem.getDate());
                }
            }
        });
    }

    private void saveLocationInToDo(Location location) {
        if (location != null) {
            todoItem.setLat(location.getLatitude());
            todoItem.setLng(location.getLongitude());
        }
    }

    private void enableToDoViewEdition(boolean isEditClicking) {
        titleEditTextView.setEnabled(isEditClicking);
        notesEditTextView.setEnabled(isEditClicking);
        dateCardView.setEnabled(isEditClicking);
        dateSwitch.setEnabled(isEditClicking);
        locationSwitch.setEnabled(isEditClicking);
        notificationSwitch.setEnabled(isEditClicking);
        editFab.setVisibility(isEditClicking ? View.INVISIBLE : View.VISIBLE);
        saveFab.setVisibility(isEditClicking ? View.VISIBLE : View.INVISIBLE);
    }

    private void saveData() {
        if (dateSwitch.isChecked()) {
            String selectedDate = dateTextView.getText().toString();
            if (selectedDate.equals(getText(R.string.date))) {
                Snackbar.make(saveFab, R.string.set_date_or_change_switch_message, Snackbar.LENGTH_LONG);
                return;
            }
            todoItem.setDate(new Date(selectedDate));
        } else {
            todoItem.setDate(null);
        }
        todoItem.setTitle(titleEditTextView.getText().toString());
        todoItem.setNotes(notesEditTextView.getText().toString());
        if (todoItem.getId() == null) {
            toDoDbHelper.saveToDo(todoItem);
        } else {
            toDoDbHelper.updateToDo(todoItem);
        }
    }

    private void populateToDoObject() {
        todoItem = getTodo();
        displayDetail();
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
            enableToDoViewEdition(false);
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        }
        enableToDoViewEdition(true);
        return new ToDoContent();
    }

    public void updateDate(Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String dateText = dateFormat.format(date);
        dateTextView.setText(dateText);
    }

    public void prepareNotification(Notification notification, Date date) {
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long remainingMillis = SystemClock.elapsedRealtime() + date.getTime();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, remainingMillis, pendingIntent);
    }

    public Notification getNotification(ToDoContent todoItem) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(todoItem.getTitle())
                .setContentText(todoItem.getDate().toString())
                .setSmallIcon(R.drawable.ic_event_note_black);
        return builder.build();
    }
}
```
Con esto ya deberiamos poder crear y editar ToDos en nuestra aplicacion, estos van a seguir existiendo aun que cierres la aplicacion o reinicies el celular ***JUST TRY IT!***

Con esto termina el code lab.
