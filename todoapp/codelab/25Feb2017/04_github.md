[new_repository]: http://image.prntscr.com/image/f097e386eca54e1e9d8ab3ad7665c25e.png
[imagen2]: http://image.prntscr.com/image/4dfc027a75d04777ade54fe8878432a8.png

[\[Paso Anterior\]](03_main_layout.md)

# GitHub

1. Necesitamos crearnos cuenta en GitHub, puedes hacerlo en: http://github.com
2. Despues de crear una cuenta, solo tenemos que crear un repositorio para el
proyecto:

![][new_repository]

3. Vamos a la terminal de IntelliJ/Android Studio para agregar el nuevo repositorio.

![][imagen2]

4. Corremos:
```bash
$ git init
$ git remote add origin https://github.com/{TU_USUARIO}/{REPOSITORIO_CREADO}.git
$ git add .
$ git commit -m "commit inicial"
$ git push -u origin master
```

Con esto podrás ver tus archivos en GitHub.
