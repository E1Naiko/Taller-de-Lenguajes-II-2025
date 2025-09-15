```mermaid
sequenceDiagram
    actor Usuario
    participant Administrador
    participant Metadatos
    participant Pelicula
    participant PlataformaTDL2

    Usuario->>Administrador: 1. gestionarAltaDePelicula(datos)
    activate Administrador

    Note over Administrador, Metadatos: Primero, se crea el objeto Metadatos
    Administrador->>Metadatos: 2. <<create>> new Metadatos(datos.info)

    Note over Administrador, Pelicula: Luego, se crea el objeto Pelicula.
    Administrador->>Pelicula: 3. <<create>> new Pelicula(datos.archivo,nuevosMetadatos)

    Administrador->>PlataformaTDL2: 5. agregarContenido(nuevaPelicula)
    activate PlataformaTDL2
    
    Note right of PlataformaTDL2: La plataforma guarda la Película, <br> que ya contiene sus Metadatos.
    
    PlataformaTDL2-->>Administrador: 6. (retorno)
    deactivate PlataformaTDL2
    
    Administrador-->>Usuario: 7. exito
    deactivate Administrador