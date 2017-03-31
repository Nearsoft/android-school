
[\[Paso Anterior\]](04_ic_pin_disabled.md)

# ToDoListAdapter.java

En nuestro `ToDoListAdapter.java` agregaremos la funcionalidad para que cuando nuestro ToDo no contenga location muestre nuestro `ic_pin_disabled.xml` que ayudara al usuario a entender que el ToDo no contiene información de location.

```java
 void bindData(ToDoContent toDoItem) {
            item = toDoItem;
            toDoNameTextBox.setText(toDoItem.getTitle());
            if (toDoItem.hasDate()) {
                dateTextBox.setText(toDoItem.getDate().toString());
            }
            doneCheckbox.setOnClickListener(this);
            mapButton.setOnClickListener(this);
            container.setOnClickListener(this);
            if(item.getLat() == 0.0d && item.getLng() == 0.0d){
                mapButton.setImageResource(R.drawable.ic_pin_disabled);
                mapButton.setEnabled(false);
            }
        }
```

Los elementos con location y sin location en la lista deben tener iconos de diferente color como en este ejemplo:

<img src="http://i.imgur.com/kqzWfCL.png" height = 500px>


Con esto terminamos este code lab de locations.
