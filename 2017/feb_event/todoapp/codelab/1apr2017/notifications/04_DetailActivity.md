[\[Paso Anterior\]](03_layout.md)

7. Dentro de **DetailActivity** agregaremos lo siguiente:
    - Declaracion de nuestro nuevo switch:
        ```java 
        private Switch notificationSwitch
        ```
    - Bind de este switch a nuestra vista:
        ```java 
        notificationSwitch = (Switch) findViewById(R.id.switch_notification);
        ```
    - Agregamos una instancia a nuestro **AlarmHandler** justo debajo de nuestro **todoDbHelper**
        ```java
            private AlarmHandler alarmHandler;
        ```
        
    - Instanciemos este **alarmHandler** en nuestro **onCreate()**
    ``` java
        alarmHandler = new AlarmHandler(this);
    ```
    - Dentro del metodo **enableToDoViewEdition** hay que habilitar el switch si se esta en modo de edición: 
        ```java
            notificationSwitch.setEnabled(isEditClicking);
        ```
    - Editamos el metodo **saveDate**
        ```java
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
                if (notificationSwitch.isChecked()) {
                    todoItem.setNotify(true);
                }
                todoItem.setTitle(titleEditTextView.getText().toString());
                todoItem.setNotes(notesEditTextView.getText().toString());
                if (todoItem.getId() == null) {
                    todoItem.setId(toDoDbHelper.saveToDo(todoItem));
                } else {
                    toDoDbHelper.updateToDo(todoItem);
                }
                alarmHandler.createOrUpdateAlarm(todoItem);
          }
        ```
    - Editamos el metodo **showDatePicker** para que la notificacion se lleve acabo 20 segundos despues de ser guardada
    ```java
        private void showDatePicker() {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.attachListener(new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR);
                int minutes = calendar.get(Calendar.MINUTE);
                int seconds = calendar.get(Calendar.SECOND) + 20;
                Date dateTime = new Date(year, month, dayOfMonth, hours, minutes, seconds);
                updateDate(dateTime);
            }
        });
        dialog.show(this.getSupportFragmentManager(), "datePicker");
    }
    ```
   
## Nuestro **DetailActivity** debe verse como [este](https://gist.github.com/Mathreyu/7216fb52e06bec624338bbc3f84a8f06)
 (hay que borrar algunos métodos que no se usarán)
<img src="http://image.prntscr.com/image/aef062f0d8af41ef8ae03e94c4b753a1.png"/>
 


[\[Siguiente Paso\]](05_MainActivity.md)
