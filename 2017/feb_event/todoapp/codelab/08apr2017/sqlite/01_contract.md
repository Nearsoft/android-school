[img_1]: http://i.imgur.com/RwJz2vU.png
# Introducción

Clona el siguiente repositorio para estar todos en el mismo canal :P

```shell
git clone https://github.com/Nearsoft/android-school.git
```
corre el siguiente comando:

```shell
git reset --hard f7e777d8c4ce80f2fdc5f6ec5a78c5dbebaf4443
```

Abre el proyecto en Android studio, la ubicación del proyecto es:

`android-school/todoapp/TODOapp`

Dale **RUN** a la aplicación y veras esto:

![img_1]

(Es donde se quedaron la clase pasada).

# ToDo Data Base Contract

Comenzaremos creando la clase `ToDoDbContract` en esta clase definiremos todas las constantes que vamos usar tanto para crear como para consultar la base de datos:

```java
package com.nearsoft.androidschool.todoapp.database;

import android.provider.BaseColumns;

public final class ToDoDbContract {

    private ToDoDbContract() {
    }

    public static class ToDoTable implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DONE = "done";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_STARRED = "starred";
        public static final String COLUMN_NAME_NOTES = "notes";
        public static final String COLUMN_NAME_NOTIFY = "notify";
    }
}
```

[\[Siguiente Paso\]](02_db_helper.md)
