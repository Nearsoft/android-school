[\[Paso Anterior\]](03_activity_detail.md)

#ic_pin_disabled.xml

crearemos un nuevo vector drawable similar al que ya tenemos pero de color gris, usaremos este icono en los elementos de la lista que no tiene location. En la carpeta de drawable crea un nuevo archivo xml 

<img src="http://i.imgur.com/GKryLQJ.png" >

 llamalo `ic_pin_disabled.xml` y agrega el siguiente contenido:


```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportHeight="24.0"
    android:viewportWidth="24.0">
    <path
        android:fillColor="@android:color/darker_gray"
        android:pathData="M18,8c0,-3.31 -2.69,-6 -6,-6S6,4.69 6,8c0,4.5 6,11 6,11s6,-6.5 6,-11zM10,8c0,-1.1 0.9,-2 2,-2s2,0.9 2,2 -0.89,2 -2,2c-1.1,0 -2,-0.9 -2,-2zM5,20v2h14v-2L5,20z" />
</vector>
```

Con esto tenemos un icono de pin deshabitado de color gris. En el preview podemos ver nuestro icono, se debe ver así:

<img src="http://i.imgur.com/QsfgTwL.png" >


[\[Siguiente Paso\]](05_todo_list_adapter.md)
