View and Create ToDo
===================


En este codeLab crearemos una nueva activity para ver los datos de nuestras ToDo que tenemos en la lista y editarlos. También usaremos esta misma activity para crear nuevas ToDo y llenar sus campos.

----------

1.- Crear nuevo paquete en la carpeta de activities 

<img src="http://i.imgur.com/kmAB9cr.png" width = 800>

nombrarla detail

<img src="http://i.imgur.com/BF69wFe.png">

2.- Crear activity bajo el nuevo paquete **detail** 

<img src="http://i.imgur.com/8V1Fa3F.png">

nombrarla **DetailActivity**. Activa la opcion de **Genarate Layout File** y **Backwards Compatibility**.

<img src="http://i.imgur.com/mrlppCS.png">

3- Dentro de ToDoListAdapter dentro de la clase ViewHolder agrega una variable para guardar el ToDo llamada **item**:

``` java
static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ToDoContent item;
        CardView container;
        TextView toDoNameTextBox;
        TextView dateTextBox;
```

extraigamos el valor de **item** desde el parametro que recibe **bindData**:
```
void bindData(ToDoContent toDoItem) {
            item = toDoItem;
            toDoNameTextBox.setText(toDoItem.getTitle());
	   …
}
```

dentro de **onClick** en la clase **ViewHolder** agregamos el siguiente código que será ejecutado cuando cada elemento de la lista sea seleccionado, esto nos llevara a la nueva instancia de **DetailActivity** y le pasara el ToDo a **detailActivity**.
``` java
  @Override
        public void onClick(View view) {
		…
        if (view.getId() == R.id.cardViewContainer) {
           Intent intent = new Intent(view.getContext(), DetailActivity.class);    
           intent.putExtra(DetailActivity.EXTRA_TODO_KEY, item);
           view.getContext().startActivity(intent);
           }
```

4- En **MainActivity** agreguemos a **addFab** la funcionalidad para que también cree una instancia de **DetailActivity** que será un nuevo ToDo:

```
 addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });
```

5- Corre la Aplicación selecciona un elemento de la lista y debería llevarte a una nueva activity. Usa el addFab y también debería llevarte a una nueva activity.

6.- crea un nuevo archivo xml bajo el paquete **layout** que vamos a utilizar para mostrar un CardView que contendrá la fecha del ToDo


<img src="http://i.imgur.com/YcOltWX.png">

nombralo **date_layout.xml** 

<img src="http://i.imgur.com/CewiJM9.png">

modifica **date_layout.xml** para que contenga el siguiente codigo:
```
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

        <!-- Add description icon -->

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

7.- modifica **activity_detail.xml** layout para que contenga el siguiente código :
```
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

        <include
            android:id="@+id/date_view"
            layout="@layout/date_layout"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="32dp"
            android:layout_below="@+id/title_field"
            android:layout_alignParentEnd="true" />
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

8.- En **DetailActivity** agrega el siguiente código. Declara un método nuevo llamado **setViews()**, en este método declararemos todas las views que están en nuestro **activity_detail.xml**:
``` java
    private EditText titleEditTextView;
    private EditText notesEditTextView;
    private CardView dateCardView;
    private TextView dateTextView;
    private FloatingActionButton editFab;
    private FloatingActionButton saveFab;
    private Switch dateSwitch;
    private Switch locationSwitch;


  @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
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
```    

Con esto ya tenemos todas nuestras views declaradas y listas para usar!

9.- Ahora hay que llenar nuestras vistas con la ToDo que nos va a llegar desde **mainActivity**, para esto vamos a declarar el ToDoContent hasta arriba de la clase
``` java
private ToDoContent todoItem;
```

Despues declaramos los siguiente metodo **populateToDoObject()** cuya responsabilidad será obtener ToDo y llenar las vistas con la información del ToDo. Este método lo llamaremos en **onCreate**:
``` java
 private void populateToDoObject() {
        todoItem = getTodo();
    }

  @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        populateToDoObject();
    }
```

Desde **populateObject()** llamamos a otro método **getToDo()** la responsabilidad de este método será obtener el ToDo que mandamos desde **mainActivity** o crear uno nuevo, para obtener el ToDo que está en el intent necesitamos un nuevo String que será el nombre con el que identificamos el contenido:
``` java
public static final String EXTRA_TODO_KEY = "TODO";
```

Después declaramos el método dentro del activity:
``` java
private ToDoContent getTodo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            enableToDoViewEdition(false);
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        }
        enableToDoViewEdition(true);
        return new ToDoContent();
    }
```

Ahora que ya obtenemos el ToDo o creamos uno nuevo podemos popular las vistas con su información, por lo que creamos otro metodo **displayDetail()**, este método se encargará de incluir la información del ToDo en las vistas:
``` java
private void displayDetail() {
        titleEditTextView.setText(todoItem.getTitle());
        notesEditTextView.setText(todoItem.getNotes());
        if (todoItem.getDate() != null) {
            updateDate(todoItem.getDate());
        }
        dateSwitch.setChecked(todoItem.hasDate());
    }
```

Al fin, llamamos este método **displayDetail()** desde **populateTodoObject()**:

``` java
 private void populateToDoObject() {
        todoItem = getTodo();
        displayDetail();
    }
```

10.- Una vez que tenemos el ToDo y llenamos la vistas con el, necesitamos un método que nos permita cambiar de modalidad de ver o editar, declaremos un metodo que se encargara de esto **enableToDoViewEdition()**:

``` java
private void enableToDoViewEdition(boolean isEditClicking) {
        titleEditTextView.setEnabled(isEditClicking);
        notesEditTextView.setEnabled(isEditClicking);
        dateCardView.setEnabled(isEditClicking);
        dateSwitch.setEnabled(isEditClicking);
        locationSwitch.setEnabled(isEditClicking);
        editFab.setVisibility(isEditClicking ? View.INVISIBLE : View.VISIBLE);
        saveFab.setVisibility(isEditClicking ? View.VISIBLE : View.INVISIBLE);
    }
```

Con esto inhabilitamos la edición de las views o la habilitamos.

11.- Para elegir la fecha en nuestro ToDo necesitamos mostrar un DatePicker por lo que declararemos el siguiente método **showDatePicker()**. para el DatePicker necesitamos un dialogFragment. Crea un nuevo paquete debajo de **todoapp**(al mismo nivel de **activities** y **models**. Llámalo **fragment**:

<img src="http://i.imgur.com/6oJ1XJY.png">

Dentro del paquete **fragment**  crea un Fragment vacío


<img src="http://i.imgur.com/qv6WkEE.png">

Llamalo **DatePickerFragment** no incluyes ninguna de las opciones:

<img src="http://i.imgur.com/2vj4KjV.png">

Copia el siguiente código en **DatePickerFragment:**

``` java
public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (listener == null) {
            throw new IllegalAccessError("listener is null, call =DatePickerFragment#attachListener first");
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

Nota es importante notar que **DatePickerFragment** extiende **DialogFragment**

12- Para grabar la información que escribimos en DetailActivity declaramos el siguiente método **saveData()** este método se encargara de tomar los textos de las vistas y ponerlas en el ToDo :
``` java
 private void saveData() {
        String selectedDate = dateTextView.getText().toString();
        if (selectedDate.equals(getText(R.string.date))) {
            Snackbar.make(saveFab, R.string.set_date_or_change_switch_message, Snackbar.LENGTH_LONG);
            return;
        }
      todoItem.setTitle(titleEditTextView.getText().toString());
        todoItem.setDate(new Date(selectedDate));
        todoItem.setNotes(notesEditTextView.getText().toString());
        todoItem.setHasDate(dateSwitch.isChecked());
    }
```

13.-Ahora que toda la funcionalidad de las vistas esta lista podemos agregar esta funcionalidad a las vistas, para esto agregaremos 4 métodos:

**setSwitchListener()** se encargara de agregar la funcionalidad a el switch cuando este sea activado o desactivado:

``` java
private void setSwitchListener() {
        dateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dateCardView.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }
```

**setSaveListener()** se encargara de configurar la funcionalidad del saveFab cuando sea presionado:
``` java
private void setSaveListener() {
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Snackbar.make(editFab, "Data Saved (Not saving in reality)", Snackbar.LENGTH_SHORT).show();
enableToDoViewEdition(false);
            }
        });
    }
``` 

**setEditListener()** se encargara de configurar la funcionalidad del editFab cuando sea presionado:
``` java
  private void setEditListener() {
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableToDoViewEdition(true);
            }
        });
    }
``` 

**setDateListener()** se encargara de configurar la funcionalidad cuando dateCardView sea presionada:
``` java
    private void setDateListener() {
        dateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }
```

14.- Ahora que tenemos nuestro **DatePickerFragment** podemos llamarlo desde DetailActivity y usarlo. Para esto declararemos el metodo **showDatePicker()** :
``` java
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
```

Cuando una fecha sea elegida queremos que este se refleje en nuestro dateTextView por lo que declaramos el siguiente método que hará este trabajo **updateDate()** :
``` java
public void updateDate(Date date) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String dateText = dateFormat.format(date);
        dateTextView.setText(dateText);
    }
```

15.- por ultimo nuestro **onCreate** se debería ver asi, agregando los métodos que declaramos en el paso 13:
``` java
  @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        populateToDoObject();
        setSwitchListener();
        setSaveListener();
        setEditListener();
        setDateListener();
    }
```
