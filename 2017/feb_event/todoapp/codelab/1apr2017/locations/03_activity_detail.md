 [\[Paso Anterior\]](02_detail_layout.md)

# DetailActivity.java

Declararemos locationSwitch, LocationManager que nos ayudara a obtener el servicio de location dentro de `DetailActivity.java` asi como 2 constantes que utilizaremos pare diferenciar los diferentes request que haremos en esta actividad y un campo para guardar el objeto Location que llamaremos userLocation.
``` java
public class DetailActivity extends AppCompatActivity {

private Switch locationSwitch;
private LocationManager locationManager;
private static final int REQUEST_LOCATION_ENABLED = 0;
private static final int REQUEST_LOCATION_PERMISSION = 1;
private Location userLocation;
``` 
Dentro de `onCreate(@Nullable Bundle savedInstanceState)` definimos nuestro LocationManager:

```java 
protected void onCreate(@Nullable Bundle savedInstanceState) {
..
locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
}
``` 

Ahora dentro del metodo `setViews()` buscaremos la vista a través del id y lo guardaremos en `locationSwitch`
```java 
private void setViews() {
..
locationSwitch = (Switch) findViewById(R.id.switch_location);
..
}
``` 

Ahora necesitamos declarar la funcionalidad de `locationSwitch` cuando este sea activado o desactivado, por lo que agregaremos un listener a `locationSwitch` dentro del método  `setSwitchListener()`:

```java
private void setSwitchListener() {
..
locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
@Override
public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {}
``` 

Por ahora vamos a dejar el listener vacio y construiremos los métodos para obtener el location y los permisos de ser necesario.

Para saber si nuestro dispositivo android tiene activado Locations accedemos a **Settings > Location**

En settings buscamos Location

<img src="http://i.imgur.com/xzT6CBi.png" height = 500px>

Desde la pantalla de Location podemos activar o desactivar Location.

<img src="http://i.imgur.com/9pQzKOQ.png" height = 500px>

pero haremos que nuestra aplicacion nos lleve a esta pantalla sin tener que abrir settings y buscar la opcion de location.

Para esto vamos a declarar un método que nos muestre un dialogo y pregunte si queremos ir a la pantalla de Location dentro de la configuración de nuestro dispositivo android. El metodo se llamara `showLocationNeededDialog()` y se vera así:

``` java
private void showLocationNeededDialog() {
final AlertDialog.Builder builder = new AlertDialog.Builder(this)
.setTitle(R.string.location_dialog_title)
.setMessage(R.string.location_dialog_message)
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

``` 

Utilizamos un `AlertDialog.Builder` que nos permite proporcionarle de una manera fácil todos los elementos que el AlertDialog tendrá como titulo, texto, botones y sus funcionalidades. El AlertDialog en acción se vera así:

<img src="http://i.imgur.com/2y4LyCM.png" height = 500px>

Lo que pasara cuando presionemos **OK** en el dialogo se ejecutara el método 
`showlocationsettings()`. En este método se llamara un **Intent** que nos llevara específicamente a la configuración de Location. 
```java
private void showLocationSettings() {
Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
startActivityForResult(intent, REQUEST_LOCATION_ENABLED);
}
``` 

Ahora que nuestra aplicación nos lleva a la configuración de location, queremos verificar que location halla sido activado, esto lo verificamos a través del método `onActivityResult(int requestCode, int resultCode, Intent data)`.  Agregamos el siguiente código dentro de este método:
```java
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
if (requestCode == REQUEST_LOCATION_ENABLED
&& locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
getLocationFromService();
} else {
locationSwitch.setChecked(false);
}
}
```

Si Location no esta activado al volver de la configuración, el switch se deshabita, en caso contrario tratara de obtener el location a través de el servicio que locationManager nos da, para obtener el location utilizamos el método `getLocationFromService()` que definiremos a continuación:

```java
private void getLocationFromService() {
if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
userLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
if (userLocation != null) {
saveLocationInToDo(userLocation);
locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
}
} else {
ActivityCompat.requestPermissions(DetailActivity.this,
new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
}
}
```

En este método trataremos de obtener el location con `locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)` si lo obtenemos por este medio guardaremos este location en nuestro ToDo con el metodo `saveLocationInToDo(userLocation)` que definiremos a continuación:

```java
private void saveLocationInToDo(Location location) {
if (location != null) {
todoItem.setLat(location.getLatitude());
todoItem.setLng(location.getLongitude());
}
}
```
En el caso de que no obtengamos location a traves de `locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)` pediremos a `locationManager`un location con `locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this)` el cual necesitara implementar un LocationListener que nos proporcionara el Location. Para implementar LocationListener agregamos a nuestra declaracion de la clase:

``` java
public class DetailActivity extends AppCompatActivity implements LocationListener {
``` 

Habiendo agregado `LocationListener` tendremos que implementar 4 métodos de esta interfaz:
``` java
@Override
public void onLocationChanged(Location location) {
if (userLocation == null) {
userLocation = location;
saveLocationInToDo(location);
}
locationManager.removeUpdates(this);
}

@Override
public void onStatusChanged(String s, int i, Bundle bundle) {}

@Override
public void onProviderEnabled(String s) {}

@Override
public void onProviderDisabled(String s) {}
```

Utilizaremos `onLocationChanged` para recibir el nuevo location en caso de no tener uno y una vez obtenido nuestro location lo guardaremos en nuestro ToDo y por ultimo desactivaremos la funcionalidad para recibir location.

En caso de que tengamos Location activado en nuestro dispositivo pero aun no le hayamos dado permiso a la aplicación para utilizarlo no podremos obtener location en el método `getLocationFromService()` por lo que  la aplicación nos pedirá permiso a través de un dialogo, esta funcionalidad esta dentro del método `getLocationFromService()` cuando la siguiente linea de código se ejecuta:
``` java
ActivityCompat.requestPermissions(DetailActivity.this,
new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
```

El dialogo que esta funcionalidad muestra se vera así:

<img src="http://i.imgur.com/7GdKSgQ.png" height = 500px>

Para verificar que efectivamente el usuario decidió dar permiso a la aplicación utilizaremos el método `onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)` que trabaja similar a onActivityResult y verificara que tengamos permiso para utilizar location después de desaparecer el dialogo del permiso, de no ser así desactivara el switch igual que onActivityResult ya que las condiciones no se cumplen para poder utilizar el servicio de location. 
```java
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
getLocationFromService();
} else {
locationSwitch.setChecked(false);
}
}
```

En caso contrario, es decir, que el usuario de permiso volveremos a ejecutará `getLocationFromService()` que esta vez con permiso del usuario obtendremos el location y lo guardaremos en nuestro ToDo.

Por ultimo nos devolvemos a el listener de nuestro locationSwitch y le proporcionamos la funcionalidad que construimos. Cuando el switch sea activado verificara si tenemos la configuración de Location habilitada, si es así obtendrá el location del servicio que tenemos en locationManager. Si no tenemos location activado mostrara el dialogo para preguntar si queremos activarlo en los ajustes de nuestro dispositivo. Si el switch es desactivado asignaremos valores de **0.0d**(sin location) a nuestro ToDo. Nuestro listener queda así:
```java
locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
@Override
public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
if (isChecked) {
if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
getLocationFromService();
} else {
showLocationNeededDialog();
}
} else {
todoItem.setLat(0.0d);
todoItem.setLng(0.0d);
}
}
});
```
Con esto terminamos la funcionalidad para guardar location dentro de nuestro ToDo.

[\[Siguiente Paso\]](04_ic_pin_disabled.md)

