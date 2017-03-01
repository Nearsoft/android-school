package com.nearsoft.androidschool.todoapp.activities.detail;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
    private Switch locationSwitch;
    private ToDoContent todoItem;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        setSwitchListener();
        populateToDoObject();
        setSaveListener();
        setEditListener();
        setDateListener();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
    }

    private void setSaveListener() {
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveData();
                    Snackbar.make(editFab, "Data Saved (Not saving in reality)", Snackbar.LENGTH_SHORT).show();
                    buttonViewConfig(false);
                } catch (Exception e) {
                    Snackbar.make(editFab, R.string.set_date_or_change_switch_message, Snackbar.LENGTH_SHORT).show();
                }
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
        locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && isChecked) {
                    showLocationNeededDialog();
                } else {
                    getLocationFromService();
                }
            }
        });
    }

    private void getLocationFromService() {
        if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            saveLocationInToDo(location);
        } else {
            ActivityCompat.requestPermissions(DetailActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    private void saveLocationInToDo(Location location) {
        if (location != null) {
            todoItem.setLat(location.getLatitude());
            todoItem.setLng(location.getLongitude());
        }
    }

    private void buttonViewConfig(boolean isEditClicking) {
        titleEditTextView.setEnabled(isEditClicking);
        notesEditTextView.setEnabled(isEditClicking);
        dateCardView.setEnabled(isEditClicking);
        dateSwitch.setEnabled(isEditClicking);
        locationSwitch.setEnabled(isEditClicking);
        editFab.setVisibility(isEditClicking ? View.INVISIBLE : View.VISIBLE);
        saveFab.setVisibility(isEditClicking ? View.VISIBLE : View.INVISIBLE);
    }

    private void saveData() throws Exception {
//        TODO:this method will make the saving
        String selectedDate = dateTextView.getText().toString();
        if (selectedDate.equals(getText(R.string.date))) {
            throw new Exception(dateCardView.getClass().getSimpleName() + getString(R.string.set_date_or_change_switch_message));
        }
        todoItem.setTitle(titleEditTextView.getText().toString());
        todoItem.setDate(new Date(selectedDate));
        todoItem.setNotes(notesEditTextView.getText().toString());
        todoItem.setHasDate(dateSwitch.isChecked());
    }

    private void populateToDoObject() {
        try {
            todoItem = getTodo();
            displayDetail();
            buttonViewConfig(false);
        } catch (Exception e) {
            todoItem = new ToDoContent();
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

    private ToDoContent getTodo() throws Exception {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        }
        throw new Exception(getClass().getSimpleName() + " intent extras should contain an item");
    }

    public void updateDate(Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String dateText = dateFormat.format(date);
        dateTextView.setText(dateText);
    }

    private void showLocationNeededDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Location enabled Needed")
                .setMessage("Do you which to go to the settings to enable Location settings?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showLocationSettings();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        locationSwitch.setChecked(false);
                    }
                });
        builder.create()
                .show();
    }

    private void showLocationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            getLocationFromService();
        } else {
            locationSwitch.setChecked(false);
        }
    }
}
