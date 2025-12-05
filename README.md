# Taller de Lenguajes II 2025
Integrantes: Alam Meza y Nicolás Peñalba
[GitHub con el proyecto completo](https://github.com/E1Naiko/Taller-de-Lenguajes-II-2025 "GitHub con el proyecto completo").

## Convenciones entregable 3
 - Hicimos uso de la libreria [OpenCSV](https://mvnrepository.com/artifact/com.opencsv/opencsv "OpenCSV") para el manejo del CSV.
 - Separamos la carga de datos en 2 etapas para reducir el tiempo de espera del usuario y aprovechar mejor del procesamiento multihilo.
  - 1) ImportarCSVaLista ->  Cargar los datos del CSV en memoria utilizando una lista.
   - 2) ImportarListaABd -> Cargar los datos en la lista resultante a la BD (evitando duplicados).
 - Se deshabilita tanto la carga de calificaciones como la busqueda en api mientras se este cargando elementos en la BD.
 - En primer carga, se muestran el top 10 peliculas con mejor rating encontradas en el CSV (por medio de la lista cargada en memoria).

## Convenciones entregable 2
 - En el manejo de usuario: en un futuro pensamos implementar los géneros preferidos por el usuario y el historial en tablas individuales en la BD, asi que por el momento no lo manejamos.
  - En metadatos respetando el UML se considera arrays el elenco y subtítulo pero en el código usamos una lista temporal y luego cambiamos a Array.
  - En Pelicula la direccion de archivo es un string lo dejamos así ya que está fuera del alcance del entregable. Tenemos planeado guardar dichas direcciones en su correspondiente tabla de la BD.
 - En Pelicula la duración que (es un timer) consideramos convertirlo a string para que lo gestione la BD y luego lo convierta de vuelta a Timer.
 - Pensamos refactorizar menuResenia.java y dividirlo en el manejo de cada segmento del programa, pero visto la mayoria de código no es nada más que el manejo de la consola lo dejamos. Somos conscientes que es un código demasiado grande y faltaria optimizarlo.
 - Estuvimos teniendo muchos problemas con el manejo del scanner, si bien funciona bien con strings sin espacios el segundo que se sale de esa convencion puede variar el resultado. Visto que en el siguiente tp pasaremos a una UI de verdad, decidimos priorizar el manejo de la bd y de los datos internos.

 ## Estructura de datos:

 - Carpeta DB: interfaces de todas las tablas pertenecientes a la base de datos y su implementación en JDBC y el factory que controla e inicia todo.

    ![alt text](image.png)  
 - Carpeta Model: contiene todos los objetos utilizados.

    ![alt text](image-1.png)
- Utilidades: contiene los comparadores de elementos y el conversor que usamos para manejar la duracion de las peliculas.

    ![alt text](image-2.png)
