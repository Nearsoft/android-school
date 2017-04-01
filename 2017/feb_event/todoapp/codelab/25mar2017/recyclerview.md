RecyclerView
======
## Setup
Primero agregamos la dependencia a nuestro archivo build.gradle (Module: app):
```
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
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.ivanebernal.androidschooltodo.MainActivity">

    <android.support.v7.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:layout_marginEnd="10dp"
        tools:layout_marginStart="10dp"
        android:id="@+id/todo_list"/>

</RelativeLayout>
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
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView toDoRecyclerView = (RecyclerView) findViewById(R.id.todo_list);
        List<ToDoItem> items = getItems();
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoRecyclerView.setAdapter(new ToDoAdapter(items));

    }

    private List<ToDoItem> getItems() {
        List<ToDoItem> items = new ArrayList<>();
        ToDoItem item1 = new ToDoItem("Hola mundo!", "25/3/2017", "Hermosillo, Son.");
        ToDoItem item2 = new ToDoItem("Soy prueba", "25/3/2017", "Hermosillo, Son.");
        ToDoItem item3 = new ToDoItem("Yo también", "25/3/2017", "Hermosillo, Son.");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        return items;
    }
}
```
En la línea ```todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);``` es donde buscamos en el Layout nuestro RecyclerView.

El LayoutManager es el que se encarga de determinar de qué forma se van a acomodar los objetos en la lista. Recordemos que puede ser en forma linear, cuadrícula uniforme o no uniforme, etc. 

Si usamos LinearLayoutManager nuestro RecyclerView se vería así:
![enter image description here](https://lh3.googleusercontent.com/-3PWmUjpv6X8/WNBViCR-hKI/AAAAAAAAbSk/IrDDeOxFmfUjhhQ_APyU6U9qT7Bsv07iwCLcB/s400/Screen+Shot+2017-03-20+at+3.18.23+PM.png "Screen Shot 2017-03-20 at 3.18.23 PM.png")

Cambiando ```new LinearLayoutManager(this)``` por ```new GridLayoutManager(this, 2)``` (donde 2 es el número de columnas) obtendremos la siguiente configuración:

![enter image description here](https://lh3.googleusercontent.com/-H7ADOLHd-CA/WNBXULt--QI/AAAAAAAAbS0/AlphXv7vuks0CkCQhKHp4k4FRIFYFOiqQCLcB/s400/Screen+Shot+2017-03-20+at+3.17.19+PM.png "Screen Shot 2017-03-20 at 3.17.19 PM.png")

Sin embargo nuestro ViewHolder está diseñado para ser utilizado de manera lineal, por lo que usaremos la primera configuración.

El adapter, que veremos a continuación, se encarga de acomodar los datos de una lista en cada uno de los ViewHolder del RecyclerView.

En nuestro método ``` getData()``` creamos una lista inicial de datos "dummy" que nos servirán para tener elementos en la lista en condiciones iniciales de nuestra aplicación.

Pero hay algo que falta ¿cómo acomodamos los datos que tenemos en nuestros ViewHolder? pues como ya mencioné antes hay que utilizar un Adapter.

## ToDoListAdapter
```java
class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoItem> items;

    public ToDoAdapter(List<ToDoItem> items) {
        this.items = new ArrayList<>();
        this.items.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.holder_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoItem item = items.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView containter;
        private CheckBox checkBox;
        private TextView titleTextView;
        private TextView dateTextView;
        private ImageButton button;
        private View holder;


        public ViewHolder(View itemView) {
            super(itemView);
            containter = (CardView) itemView.findViewById(R.id.container); 
            checkBox = (CheckBox) itemView.findViewById(R.id.is_done_check_box);
            titleTextView = (TextView) itemView.findViewById(R.id.todo_text);
            dateTextView = (TextView) itemView.findViewById(R.id.date_text);
            button = (ImageButton) itemView.findViewById(R.id.image_button);
            holder = itemView;
        }

        public void bindData(ToDoItem item){
            titleTextView.setText(item.getTitle());
            dateTextView.setText(item.getDate());
            containter.setOnClickListener(this);
            button.setOnClickListener(this);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.is_done_check_box){
                Toast.makeText(view.getContext(), "This should hide view", Toast.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.image_button){
                Toast.makeText(view.getContext(), "This should open MapsActivity", Toast.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.container){
                Toast.makeText(view.getContext(), "This should open DetailsActivity", Toast.LENGTH_SHORT).show();
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

El utilizar una subclase ViewHolder es una forma de implementar un patrón llamado ViewHolder pattern el cual optimiza el procesamiento de la lista ya que internamente el sistema no llama a ```findViewById``` por cada elemento en la lista sino que se llaman en ```onCreateViewHolder``` y se reutilizan cada vez que sea necesario. Se crea la cantidad de ViewHolders que sean necesarios para llenar la pantalla y se van reciclando con información distinta conforme nos desplacemos en la lista.

Hacemos también Override al método ```onClick``` y lo único especial que hay que hacer es identificar qué vista fue presionada por medio de su ID. Dependiendo de lo que presionemos es la Activity que vamos a abrir.

El producto final debería verse como la siguiente imagen:

![enter image description here](https://lh3.googleusercontent.com/mC294EXkHR5nsbEWiJkC9ZjwIe6apy8BxeWEJXiqiqIbY5toSrRjP3YWrVVq4LRRNEVw_SOLCQ=s600 "Screen Shot 2017-03-20 at 2.42.20 PM.png")
