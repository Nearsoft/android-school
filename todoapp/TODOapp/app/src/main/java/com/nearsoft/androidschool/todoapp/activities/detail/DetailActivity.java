package com.nearsoft.androidschool.todoapp.activities.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.fragment.DatePickerFragment;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

import java.util.Date;

import timber.log.Timber;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "TODO";

    private EditText titleEditTextView;
    private EditText notesEditTextView;
    private CardView dateCardView;
    private TextView dateTextView;
    private FloatingActionButton editFab;
    private FloatingActionButton saveFab;

    private ToDoContent todoItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        populateToDoObject();
        setSaveListener();
        setEditListener();
        setDateListener();
    }

    private void setViews() {
        setContentView(R.layout.activity_detail);
        titleEditTextView = (EditText) findViewById(R.id.title_field);
        notesEditTextView = (EditText) findViewById(R.id.notes_field);
        dateCardView = (CardView) findViewById(R.id.date);
        editFab = (FloatingActionButton) findViewById(R.id.edit_fab);
        saveFab = (FloatingActionButton) findViewById(R.id.save_fab);
        dateTextView = (TextView) dateCardView.findViewById(R.id.date_text);
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

            }
        });
    }

    private void showDatepicker() {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.attachListener(new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                DateTime dateTime = new DateTime(year, month + 1, dayOfMonth, 0, 0);
                Timber.d(year + "/" + month + "/" + dayOfMonth);
//                dateTextView.setText(dateTime.toDate().toString());
                updateDate(dateTime.toDate());
            }
        });
        dialog.show(this.getSupportFragmentManager(), "datePicker");
    }

    private void buttonViewConfig(boolean isEditClicking) {
        titleEditTextView.setEnabled(isEditClicking);
        notesEditTextView.setEnabled(isEditClicking);
        editFab.setVisibility(isEditClicking ? View.INVISIBLE : View.VISIBLE);
        saveFab.setVisibility(isEditClicking ? View.VISIBLE : View.INVISIBLE);
    }

    private void saveData() {
//        TODO:this method will make the saving
        todoItem.setTitle(titleEditTextView.getText().toString());
        todoItem.setDate(new Date(dateTextView.getText().toString()));
        todoItem.setNotes(notesEditTextView.getText().toString());
    }

    private void populateToDoObject() {
        try {
            todoItem = getTodo();
            displayDetail();
        } catch (AssertionError err) {
            todoItem = new ToDoContent();
            buttonViewConfig(true);
        }
    }

    private void displayDetail() {
        titleEditTextView.setText(todoItem.getTitle());
        notesEditTextView.setText(todoItem.getNotes());
        dateTextView.setText(todoItem.getDate().toString());
    }

    private ToDoContent getTodo() throws AssertionError {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        }
        throw new AssertionError(getClass().getSimpleName() + " intent extras should contain an item");
    }

    public void updateDate(Date date) {
        DateTime dateTime = new DateTime(date);
        String dateText;
        if(dateTime.get(DateTimeFieldType.dayOfYear()) == DateTime.now().get(DateTimeFieldType.dayOfYear())) {
            dateText = "Today";
        } else if(dateTime.get(DateTimeFieldType.dayOfYear()) == DateTime.now().plusDays(1).get(DateTimeFieldType.dayOfYear())) {
            dateText = "Tomorrow";
        } else {
            dateText = dateTime.toString("MM/dd/YYYY");
        }
        dateTextView.setText(dateText);
    }

}
