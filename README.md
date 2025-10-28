# Taller de Lenguajes II 2025
Integrantes: Alam Meza y Nicolás Peñalba


## Apuntes de entregable 2

 - Bien definida la coneccion entre clases y bd (modelo de clases y modelo de identidad de base de datos).
 - por cada DAO una implementacion (JDBC).
 - a medida que implementemos la logica no quede sobrecargada.
 - Importante que cuando implemente los daos, los mismos se implementen a nivel de objetos.
 - En parte de usuario, listaPreferida y historial se considera como String porque ya esta fuera del alcance del entregable 2
 - En metadatos respetando el UML se considera arrays el elenco y subtitulo pero en el codigo usamos una lista temporal y luego cambiamos a Array.
 - en Pelicula la direccion de archivo es un string lo dejamos asi ya que esta fuera del alcance del entregable.
 - tambien en la parte de duración que es un timer consideramos convertirlo a string para que lo gestione la BD y luego lo convierta de vuelta a Timer 