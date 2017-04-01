[\[Paso Anterior\]](02_main_activity.md)

# ToDoListAdapter Update
- Haremos unos cambios en `ToDoListAdapter.java`

```java
package com.nearsoft.androidschool.todoapp.activities.main.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private List<ToDoContent> toDoList;

    public ToDoListAdapter(List<ToDoContent> toDoItemList) {
        toDoList = toDoItemList;
    }

    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bindData(toDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView container;
        TextView toDoNameTextBox;
        TextView dateTextBox;
        CheckBox doneCheckbox;
        ImageButton mapButton;

        ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.cardViewContainer);
            toDoNameTextBox = (TextView) itemView.findViewById(R.id.toDoText);
            dateTextBox = (TextView) itemView.findViewById(R.id.dateText);
            doneCheckbox = (CheckBox) itemView.findViewById(R.id.isDoneCheckbox);
            mapButton = (ImageButton) itemView.findViewById(R.id.mapImageButton);
        }

        void bindData(ToDoContent toDoItem) {
            toDoNameTextBox.setText(toDoItem.getTitle());
            dateTextBox.setText(toDoItem.getDate());
            doneCheckbox.setOnClickListener(this);
            mapButton.setOnClickListener(this);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.isDoneCheckbox) {
                Snackbar.make(mapButton, "this should hide this item when clicked maybe", Snackbar.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.mapImageButton) {
                Snackbar.make(mapButton, "this should show the map in google maps", Snackbar.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.cardViewContainer) {
                Snackbar.make(mapButton, "This should take you to the detail", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}

```

- Tendremos algunos errores, los errores son por que aun no existe la actividad de Detalle.

`import com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity;`

Esta es la linea problematica, incluyendo sus usos.

Veamos lo nuevo en el adapter:

```java
Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
intent.putExtra(DetailActivity.EXTRA_TODO_KEY, item);
itemView.getContext().startActivity(intent);
```

Esto lo que hara, es que iniciará una nueva actividad de Detalle.

Con esto podemos continuar.

[\[Siguiente Paso\]](04_new_activity.md)
