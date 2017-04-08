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

<img src="http://image.prntscr.com/image/aef062f0d8af41ef8ae03e94c4b753a1.png"/>
 


[\[Siguiente Paso\]](05_MainActivity.md)
