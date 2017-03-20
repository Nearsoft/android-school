
RecyclerView
======
## Setup
Primero agregamos la dependencia a nuestro archivo build.gradle (Module: app):
```java
dependencies {
    ...
    compile 'com.android.support:recyclerview-v7:25.1.1'
    ...
}
```

Esto nos permitirá utilizar el tag ```<android.support.v7.widget.RecyclerView/>``` en el archivo de layout para nuestra MainActivity. También nos permite utilizar ```RecyclerView``` en nuestra main activity.

## activity_todomain.xml
El código de activity_todomain.xml es muy sencillo:
```java
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_todo_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.nearsoft.androidschool.todoapp.activities.main.MainActivity">


    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp"
        android:scrollbars="vertical" />


    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add_white"
        app:layout_anchor="@id/recycler_view"
        app:layout_anchorGravity="bottom|right|end" />

</android.support.design.widget.CoordinatorLayout>
```

Es importante asignar un ID al RecyclerView ya que de esta manera podremos hacerle referencia en nuestra activity.

Antes de pasar a nuestra activity hay que revisar qué compone nuestro ToDoContent:

## ToDoContent
```java
public class ToDoContent implements Serializable {
    private String title;
    private Date date;
    private boolean done;
    private double lat;
    private double lng;
    private boolean starred;
    private String notes;
    private boolean hasDate;

    public ToDoContent(String title, Date date, boolean hasDate, double lat, double lng) {
        this.title = title;
        this.date = date;
        this.hasDate = hasDate;
        this.lat = lat;
        this.lng = lng;
    }

//Getters and setters
```
Nuestra data consiste en:
* Título
* Fecha
* Terminado
* Latitud y longitud
* Un booleano para indicar si el objeto es destacado
* Notas
* Un booleano para indicar si tiene fecha

## Activity
Teniendo claro qué es lo que queremos guardar hay que implementar la lógica del RecyclerView en nuestra clase MainActivity. 

```java
public class MainActivity extends AppCompatActivity {

    private ToDoListAdapter adapter;
    private RecyclerView todoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);
        
        todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new ToDoListAdapter(getData());
        todoRecyclerView.setAdapter(adapter);
    }

    public List<ToDoContent> getData() {
        List<ToDoContent> data = new ArrayList<>();
        ToDoContent first = new ToDoContent("task 1", new Date(), true, 29.09747, -111.02198);
        first.setNotes("sample text, text sample, hehe hehe\nmore text, here is another text and more samples\nsampletext, stub, lalala i hate the word \"fake\"");
        data.add(first);
        data.add(new ToDoContent("task 2", null, false,  29.09747, -111.02198));
        data.add(new ToDoContent("task 3", null, false, 29.09747, -111.02198));
        return data;
    }
}
```
En la línea ```todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);``` es donde buscamos en el Layout nuestro RecyclerView.

El LayoutManager es el que se encarga de determinar de qué forma se van a acomodar los objetos en la lista. Recordemos que puede ser en forma linear, cuadrícula uniforme o no uniforme, etc.

El adapter, que veremos a continuación, se encarga de acomodar los datos de una lista en cada uno de los ViewHolder del RecyclerView.

En nuestro método ``` getData()``` creamos una lista inicial de datos "dummy" que nos servirán para tener elementos en la lista en condiciones iniciales de nuestra aplicación.

Pero hay algo que falta ¿cómo acomodamos los datos que tenemos en nuestros ViewHolder? pues como ya mencioné antes hay que utilizar un Adapter.

## ToDoListAdapter
```java
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

        ToDoContent item;
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
            item = toDoItem;
            toDoNameTextBox.setText(toDoItem.getTitle());
            if (toDoItem.hasDate()) {
                dateTextBox.setText(toDoItem.getDate().toString());
            }
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
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra(MapsActivity.CONTENT_EXTRA, item);
                view.getContext().startActivity(intent);
            }
            if (view.getId() == R.id.cardViewContainer) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TODO_KEY, item);
                view.getContext().startActivity(intent);
            }
        }
    }
}
```

Como pudimos observar en nuestra MainActivity, el constructor de ToDoListAdapter recibe como parámetro una lista de ToDoContent, esta lista la estaremos utilizando a través de toda la clase, por lo que hay que declararla como campo.
En el método ```onCreateViewHolder``` nos encargaremos de inflar el Layout de nuestro ViewHolder (el cuál creamos anteriormente).
En ```onBindViewHolder``` nos encargamos de hacer el data binding del respectivo objeto ToDoContent. La variable position nos permite obtener el elemento de la lista para hacer el binding. El método ```bindData``` creado en nuestra subclase ViewHolder se encarga de sacar los datos de ToDoContent y desplegarlos en la UI de nuestro elemento en la lista.

```getItemCount``` le dirá al RecyclerView cuántos elementos debe de desplegar, por lo que lo único que hay que devolver es el tamaño de nuestra lista.

Además de todos estos métodos creamos una subclase llamada ViewHolder en la que declaramos todos los Views que lleva y creamos el método antes mencionado ```bindData``` que desplegará los daros donde sea necesario. 

Hacemos también Override al método ```onClick``` y lo único especial que hay que hacer es identificar qué vista fue presionada por medio de su ID. Dependiendo de lo que presionemos es la Activity que vamos a abrir.

El producto final debería verse como la siguiente imagen:

![enter image description here](https://lh3.googleusercontent.com/mC294EXkHR5nsbEWiJkC9ZjwIe6apy8BxeWEJXiqiqIbY5toSrRjP3YWrVVq4LRRNEVw_SOLCQ=s600 "Screen Shot 2017-03-20 at 2.42.20 PM.png")
