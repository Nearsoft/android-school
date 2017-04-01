[img_1]: http://i.imgur.com/5fH0l0M.png
# Introducción

Clona el siguiente repositorio para estar sincronizados todos.

```shell
git clone https://github.com/Nearsoft/android-school.git
```

y ejecuta el siguiente comando:

```shell
git reset --hard 9c330b11112d003a96ef78eeab4c5a70353d3e86
```

Abre el proyecto en Android Studio, y listo, puedes seguir el Code Lab desde este punto.

La direccion donde esta guardado el proyecto es:

`android-school/todoapp/TODOapp`

Demosle **RUN** a la aplicación y veremos esto:

![img_1]

Si le das click a los Todos, no podras acceder a la pantalla de detalle, eso es lo que haremos en el siguiente code lab.

Procura commitear al final de cada ejercicio, para poder ver la diferencia.

```bash
# agrega todos los archivos, no importa.
git add .
```

```bash
# mensaje del commit entre comillas simples: 'mensaje'
git commit -m 'message'
```

# READ Operation

## Model

Recuerda, es importante tener un lenguaje en común, asi que vamos a verificar que nuestros modelos esten definidos correctamente.

Vamos a empezar modificando la clase `TodoContent.java` para que luzca algo asi:

```java
package com.nearsoft.androidschool.todoapp.models;

public class ToDoContent implements Serializable {
    private String title;
    private String date;
    private boolean done;
    private String location;
    private boolean starred;
    private String notes;

    public ToDoContent(String title, String date, String location) {
        this.title = title;
        this.date = date;
        this.location = location;
    }

    public ToDoContent() {

    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
```

Una vez que tengamos esto, continuamos.

[\[Siguiente Paso\]](02_main_activty.md)
