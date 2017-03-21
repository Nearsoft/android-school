
Bienvenido a este codelab de location. 

presentacion de conceptos nuevos para este code lab: http://slides.com/roymontoyamontoya/deck-4#/

Este code lab cuenta con 5 pasos siendo este el primero. Vamos a empezar actualizando nuestro Modelo ToDo.

#ToDoContent
En `ToDoContent.java` vamos a agregar 2 propiedades nuevas latitude y longitud con estos datos podemos contruir un Location. Tambien agregaremos un nuevo contructor que acepte estes datos y sus respectivos getter y setter.
```java
public class ToDoContent implements Serializable {
..
private double lat;
private double lng;
..
  public ToDoContent(String title, Date date, boolean hasDate, double lat, double lng) {
        this.title = title;
        this.date = date;
        this.hasDate = hasDate;
        this.lat = lat;
        this.lng = lng;
    }
..
public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
```

Ahora ya podemos guardar latitude y longitud en nuestro ToDo.

[\[Siguiente Paso\]](02_detail_layout.md)


